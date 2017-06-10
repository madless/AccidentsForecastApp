package com.madless.accidentsforecast;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.madless.accidentsforecast.db_new.DbAdapter;
import com.madless.accidentsforecast.model.Accident;
import com.madless.accidentsforecast.model.Cluster;
import com.madless.accidentsforecast.model.UkraineDistrict;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.Loggr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 6/2/2017.
 */

public class RepositoryManager {

    private Loggr loggr = new Loggr(this.getClass());
    private static RepositoryManager instance = new RepositoryManager();

    private List<Accident> accidents = new ArrayList<>();
    private List<LatLng> points = new ArrayList<>();
    private List<Cluster> clusters = new ArrayList<>();
    private List<UkraineDistrict> districts = new ArrayList<>();
    private Map<Integer, List<Accident>> accidentsByCluster = new HashMap<>();

    Map<Integer, Map<String, List<Accident>>> accidentsByMonthByCluster = new HashMap<>(); // Integer key - clusterid, String key - month

    public static RepositoryManager get() {
        return instance;
    }

    private RepositoryManager() {}

    public Observable preload(final Context context) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                final DbAdapter dbAdapter = new DbAdapter(context);
                if(!dbAdapter.isDbExists()) {
                    loggr.d("Loading started: " + Thread.currentThread().getName());
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    final StorageReference storageRef = storage.getReferenceFromUrl(Const.STORAGE_BASE_URL);
                    final StorageReference dataRef = storageRef.child(Const.STORAGE_DATA_FOLDER);
                    final StorageReference accidentsRef = dataRef.child(Const.STORAGE_DATA_FILE);
                    File root = context.getFilesDir();
                    File file = new File(root, Const.DB_DATA_FILENAME);
                    accidentsRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Observable.create(new ObservableOnSubscribe<Object>() {
                                @Override
                                public void subscribe(ObservableEmitter<Object> e) throws Exception {
                                    loggr.d("Loaded " + taskSnapshot.getTotalByteCount() + " bytes");
                                    loggr.d("File opening..."  + Thread.currentThread().getName());
                                    File root = context.getFilesDir();
                                    File file = new File(root, Const.DB_DATA_FILENAME);
                                    FileInputStream inputStream;
                                    try {
                                        loggr.d("Init db "  + Thread.currentThread().getName());
                                        inputStream = new FileInputStream(file);
                                        dbAdapter.createDatabase(inputStream);
                                        fillLists(dbAdapter);
                                    } catch (FileNotFoundException ex) {
                                        ex.printStackTrace();
                                        emitter.onError(ex);
                                    }
                                    emitter.onComplete();
                                }
                            }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Object>() {
                                @Override
                                public void accept(Object o) throws Exception {}
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                    emitter.onError(throwable);
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }
                    });
                } else {
                    fillLists(dbAdapter);
                    emitter.onComplete();
                }
            }
        });
    }

    private void fillLists(DbAdapter dbAdapter) {
        loggr.d("Filling lists...");
        dbAdapter.open();
        dbAdapter.fillAccidents(accidentsByCluster, points);
        clusters = dbAdapter.getClusters();
        points = dbAdapter.getPoints();
        districts = dbAdapter.getDistricts();
        loggr.d("Lists filled!");
//        loggr.d("Accidents size: " + accidents.size());
        loggr.d("Clusters size: " + clusters.size());
        dbAdapter.close();
    }

    public List<Accident> getAccidents() {
        return accidents;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public List<UkraineDistrict> getDistricts() {
        return districts;
    }

    public List<Accident> getAccidentsByClusterId(int clusterId) {
        return accidentsByCluster.get(clusterId);
    }
}
