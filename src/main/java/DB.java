import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;
import java.net.URI;
import java.net.URISyntaxException;
public class DB {

    public static Sql2o sql2o;

    static {
        Logger logger = LoggerFactory.getLogger(DB.class);

        try {
            URI dbUri;
            if (System.getenv("DATABASE_URL") == null) {
              //  dbUri = new URI("postgres://localhost:5432/wildlife_tracker");
             dbUri = new URI("postgres://vmckepavlgivnd:ae3d1fab99794458f161af8d35b185cce0b16268764852452fb8c0961225c88a@ec2-18-214-195-34.compute-1.amazonaws.com:5432/d7hp0stckob4v4");

            } else {
                dbUri = new URI(System.getenv("DATABASE_URL"));

            }
            int port = dbUri.getPort();
            String host = dbUri.getHost();
            String path = dbUri.getPath();
            String username = (dbUri.getUserInfo() == null) ? DatabaseProps.username : dbUri.getUserInfo().split(":")[0];
            String password = (dbUri.getUserInfo() == null) ? DatabaseProps.password : dbUri.getUserInfo().split(":")[1];
            sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
        } catch (URISyntaxException e) {
            logger.error("Unable to connect to database.");
        }
    }
}