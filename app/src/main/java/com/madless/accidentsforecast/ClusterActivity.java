package com.madless.accidentsforecast;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.madless.accidentsforecast.model.Accident;
import com.madless.accidentsforecast.utils.Const;

import java.util.List;

/**
 * Created by User on 10.06.2017.
 */

public class ClusterActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster);
        int clusterId = getIntent().getIntExtra(Const.EXTRA_CLUSTER_ID, -1);
        loggr.d("Cluster id: " + clusterId);
        List<Accident> accidents = RepositoryManager.get().getAccidentsByClusterId(clusterId);
        loggr.d("Accidents size: " + accidents.size());
    }
}
