package web;

import neo4j.Neo4jDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexRestController {
    @Autowired
    private Neo4jDriver neo4jDriver;
    @Cacheable(cacheNames = "getdata")
    @RequestMapping(value = "/api/getData" , method = RequestMethod.POST ,produces = "application/json")
    public HashMap<String, List<HashMap<String,Object>>> getData(@RequestParam("name_1") String name1,@RequestParam("name_2") String name2,@RequestParam("edge") int edge) {
        //HashMap<String, HashMap<String, List<HashMap<String,Object>>>> hashMap = HashMap<String, List<HashMap<String,Object>>>
        HashMap<String, List<HashMap<String,Object>>> hashMap = neo4jDriver.getresult(name1,name2,1,edge);
        return hashMap;
    }
    @Cacheable(cacheNames = "getonenodedata")
    @RequestMapping(value = "/api/getOneNodeData" , method = RequestMethod.POST ,produces = "application/json")
    public HashMap<String, List<HashMap<String,Object>>> getOneNodeData(@RequestParam("name_1") String name1,@RequestParam("edge") int edge) {
        HashMap<String, HashMap<String, List<HashMap<String,Object>>>> hashMap = new HashMap();
        return neo4jDriver.getOneNoderesult(name1,1,edge);
    }
    @Cacheable(cacheNames = "getdatapart")
    @RequestMapping(value = "/api/getDataPart" , method = RequestMethod.POST ,produces = "application/json")
    public HashMap<String, List<HashMap<String,Object>>> getDataPart(@RequestParam("name_1") String name1,@RequestParam("name_2") String name2,@RequestParam("edge_1") int edge1,@RequestParam("edge_2") int edge2) {
        HashMap<String, HashMap<String, List<HashMap<String,Object>>>> hashMap = new HashMap();
        return neo4jDriver.getresult(name1,name2,edge1,edge2);
    }
    @Cacheable(cacheNames = "getonenodedatapart")
    @RequestMapping(value = "/api/getOneNodeDataPart" , method = RequestMethod.POST ,produces = "application/json")
    public HashMap<String, List<HashMap<String,Object>>> getOneNodeDataPart(@RequestParam("name_1") String name1,@RequestParam("edge_1") int edge1,@RequestParam("edge_2") int edge2) {
        HashMap<String, HashMap<String, List<HashMap<String,Object>>>> hashMap = new HashMap();
        return neo4jDriver.getOneNoderesult(name1,edge1,edge2);
    }
}
