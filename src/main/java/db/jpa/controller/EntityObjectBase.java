package db.jpa.controller;


import com.sun.istack.internal.NotNull;
import db.jpa.dao.GenericDAO;
import db.jpa.entity.Clusters;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

@Getter
@Setter
public class EntityObjectBase {

    private static final Logger LOGGER = Logger.getLogger(EntityObjectBase.class);

    protected GenericDAO<Clusters> clusters;

    //Clusters
    protected String poltavaPivden = "Полтава-Південь";

    public final Integer getClusterByName(@NotNull final String clusterName) {
        try {
            return clusters
                    .getAll()
                    .stream()
                    .filter(f -> f.getClusterName().equalsIgnoreCase(clusterName))
                    .findFirst()
                    .orElse(null)
                    .getClusterID();
        } catch (NullPointerException e) {
            LOGGER.warn("Clusters table is NULL !!");
        }
        return null;
    }
}
