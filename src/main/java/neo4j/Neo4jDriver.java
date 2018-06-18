package neo4j;

import org.neo4j.driver.internal.value.PathValue;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.types.Node;
import org.neo4j.driver.v1.types.Path;
import org.neo4j.driver.v1.types.Relationship;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.neo4j.driver.v1.Values.parameters;
@Component
public class Neo4jDriver {
    public HashMap<String, List<HashMap<String,Object>>> getresult(String name1, String name2) {
        Driver driver = GraphDatabase.driver("bolt://149.28.125.166:7687",
                AuthTokens.basic("neo4j","pzkpfw38t"));
        Map<Long,String> map = new HashMap<>();
        HashMap<String, List<HashMap<String,Object>>> resultgraph = new HashMap<>();
        try(Session session = driver.session()){
            try (Transaction tx = session.beginTransaction()){
                StatementResult result = tx.run("Match p=(n {Name:$name1 })-[r*1..3]-(m {Name:$name2}) return p as nodesrelation",
                        parameters("name1",name1,"name2",name2));
                while(result.hasNext()){
                    Record record = result.next();
                    Path path = record.get("nodesrelation").asPath();
                    List<HashMap<String,Object>> allnodes = new ArrayList<>();
                    Iterable<Node> nodes = path.nodes();
                    for(Node node:nodes) {
                        HashMap<String,Object> nod = new HashMap();
                        nod.put("id",node.id());
                        nod.put("name",node.get("Name").toString());
                        allnodes.add(nod);
                    }
                    resultgraph.put("nodes",allnodes);
                    Iterable<Relationship> relations = path.relationships();
                    List<HashMap<String,Object>> allrelations = new ArrayList<>();
                    for(Relationship relationship:relations) {
                        HashMap<String,Object> rela = new HashMap();
                        rela.put("source",relationship.startNodeId());
                        rela.put("target",relationship.endNodeId());
                        rela.put("type",relationship.type());
                        allrelations.add(rela);
                    }
                    resultgraph.put("links",allrelations);
                    return resultgraph;
                }

            }
        }
        driver.close();
        return null;
    }

    public static void main(String[] args) {
        Neo4jDriver neo4jDriver = new Neo4jDriver();
        neo4jDriver.getresult("The_New_England_Journal_of_Medicine","Jeffrey_M._Drazen");
    }
}
