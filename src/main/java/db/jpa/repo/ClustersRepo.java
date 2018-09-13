package db.jpa.repo;

import db.jpa.entity.Clusters;

import static db.jpa.entity.Clusters.Builder.clusters;

public class ClustersRepo {

    public static Clusters getClusters(final Integer clusterID,
                                       final String clusterName,
                                       final String clusterShortName,
                                       final Double experimentalAreaPercentage,
                                       final Integer isDeleted) {
        return clusters()
                .withClusterID(clusterID)
                .withClusterName(clusterName)
                .withClusterShortName(clusterShortName)
                .withExperimentalAreaPercentage(experimentalAreaPercentage)
                .withIsDeleted(isDeleted)
                .withLastChangedOn("")
                .build();
    }
}
