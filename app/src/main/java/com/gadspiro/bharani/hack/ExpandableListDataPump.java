package com.gadspiro.bharani.hack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bharani on 12/2/17.
 */


public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");


        List<String> football = new ArrayList<String>();
        football.add("Brazil");


        List<String> basketball = new ArrayList<String>();
        basketball.add("United States");

        expandableListDetail.put("Driving License", cricket);
        expandableListDetail.put("PAN Card", football);
        return expandableListDetail;
    }
}
