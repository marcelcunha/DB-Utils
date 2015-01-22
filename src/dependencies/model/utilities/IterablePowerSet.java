package dependencies.model.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcel
 */
public class IterablePowerSet{
       
public static <T> List<Set<T>> powerset(Collection<T> list) {
    List<Set<T>> ps = new ArrayList<>();
    ps.add(new HashSet<T>());   // add the empty set

    // for every item in the original list
    for (T item : list) {
        List<Set<T>> newPs = new ArrayList<>();

        for (Set<T> subset : ps) {
            // copy all of the current powerset's subsets
            newPs.add(subset);

            // plus the subsets appended with the current item
            Set<T> newSubset = new HashSet<>(subset);
            newSubset.add(item);
            newPs.add(newSubset);
        }

        // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
        ps = newPs;
    }
    ps.sort(new Comparator<Set<T>>(){

        @Override
        public int compare(Set<T> o1, Set<T> o2) {
            if(o1.size()>o2.size())
                return 1;
            if(o2.size()>o1.size())
                return -1;
            return 0;
        }
    });
    ps.remove(ps.get(0));
    return ps;
} 
}
 