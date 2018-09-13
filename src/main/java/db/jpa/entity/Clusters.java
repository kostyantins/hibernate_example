package db.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EnterpriseDB.dbo.Clusters")
public class Clusters {

    public Clusters(Integer clusterID, String clusterName, String clusterShortName, Double experimentalAreaPercentage, Integer isDeleted, String lastChangedOn) {
        this.clusterID = clusterID;
        this.clusterName = clusterName;
        this.clusterShortName = clusterShortName;
        this.experimentalAreaPercentage = experimentalAreaPercentage;
        this.isDeleted = isDeleted;
        this.lastChangedOn = lastChangedOn;
    }

    @Id
    @Column(name = "ClusterID")
    private Integer clusterID;

    @Column(name = "ClusterName")
    private String clusterName;

    @Column(name = "ClusterShortName")
    private String clusterShortName;

    @Column(name = "ExperimentalAreaPercentage")
    private Double experimentalAreaPercentage;

    @Column(name = "IsDeleted")
    private Integer isDeleted;

    @Column(name = "LastChangedOn")
    private String lastChangedOn;

    public static interface ClusterIDStep {
        ClusterNameStep withClusterID(Integer clusterID);
    }

    public static interface ClusterNameStep {
        ClusterShortNameStep withClusterName(String clusterName);
    }

    public static interface ClusterShortNameStep {
        ExperimentalAreaPercentageStep withClusterShortName(String clusterShortName);
    }

    public static interface ExperimentalAreaPercentageStep {
        IsDeletedStep withExperimentalAreaPercentage(Double experimentalAreaPercentage);
    }

    public static interface IsDeletedStep {
        LastChangedOnStep withIsDeleted(Integer isDeleted);
    }

    public static interface LastChangedOnStep {
        BuildStep withLastChangedOn(String lastChangedOn);
    }

    public static interface BuildStep {
        Clusters build();
    }


    public static class Builder implements ClusterIDStep, ClusterNameStep, ClusterShortNameStep, ExperimentalAreaPercentageStep, IsDeletedStep, LastChangedOnStep, BuildStep {
        private Integer clusterID;
        private String clusterName;
        private String clusterShortName;
        private Double experimentalAreaPercentage;
        private Integer isDeleted;
        private String lastChangedOn;

        private Builder() {
        }

        public static ClusterIDStep clusters() {
            return new Builder();
        }

        @Override
        public ClusterNameStep withClusterID(Integer clusterID) {
            this.clusterID = clusterID;
            return this;
        }

        @Override
        public ClusterShortNameStep withClusterName(String clusterName) {
            this.clusterName = clusterName;
            return this;
        }

        @Override
        public ExperimentalAreaPercentageStep withClusterShortName(String clusterShortName) {
            this.clusterShortName = clusterShortName;
            return this;
        }

        @Override
        public IsDeletedStep withExperimentalAreaPercentage(Double experimentalAreaPercentage) {
            this.experimentalAreaPercentage = experimentalAreaPercentage;
            return this;
        }

        @Override
        public LastChangedOnStep withIsDeleted(Integer isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        @Override
        public BuildStep withLastChangedOn(String lastChangedOn) {
            this.lastChangedOn = lastChangedOn;
            return this;
        }

        @Override
        public Clusters build() {
            Clusters clusters = new Clusters();
            clusters.setClusterID(this.clusterID);
            clusters.setClusterName(this.clusterName);
            clusters.setClusterShortName(this.clusterShortName);
            clusters.setExperimentalAreaPercentage(this.experimentalAreaPercentage);
            clusters.setIsDeleted(this.isDeleted);
            clusters.setLastChangedOn(this.lastChangedOn);
            return clusters;
        }
    }
}
