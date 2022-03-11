/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sorleto_domenico
 */
public class JOpenStreetMap {

    public class place {

        public String amenity;
        public String road;
        public String town;
        public String county;
        public String state;
        public int postcode;
        public String country;
        public String country_code;
        public int place_id;
        public String osm_type;
        public int osm_id;
        public int place_rank;
        public int address_rank;
        public String boundingbox;
        public double lat;
        public double lon;
        public String display_name;
        public String _class;
        public String type;
        public double importance;
        public String icon;
        public String text;
        public String building;
        public int house_number;
        public String village;
    }

    public class searchresults {

        public List<place> place;
        public Date timestamp;
        public String attribution;
        public String querystring;
        public double exclude_place_ids;
        public String more_url;
        public String text;

        @Override
        public String toString() {
            String s = "";
            if (place.size() > 0) {
                for (int i = 0; i < place.size(); i++) {
                    s += place.get(i).toString();
                }
            }
            return s;

        }
    }

}
