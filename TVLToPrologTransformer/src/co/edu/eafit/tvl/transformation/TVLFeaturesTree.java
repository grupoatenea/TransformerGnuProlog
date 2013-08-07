package co.edu.eafit.tvl.transformation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import be.ac.info.fundp.TVLParser.symbolTables.FeatureSymbol;

public class TVLFeaturesTree {
    
	private FeatureSymbol root;
    
    /**
     * Default ctor.
     */
    public TVLFeaturesTree(FeatureSymbol root) {
    	if (root == null) throw new NullPointerException("Root feature is null");
        this.root = root;
    }
 
 
    /**
     * Returns the Tree<T> as a List of Node<T> objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List<Node<T>>.
     */
    public List<FeatureSymbol> toList() {
        List<FeatureSymbol> list = new ArrayList<FeatureSymbol>();
        walk(root, list);
        return list;
    }
     
    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public String toBooleanForm() {
        return toList().toString();
    }
     
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(FeatureSymbol element, List<FeatureSymbol> list) {
        list.add(element);
        if ( element.getChildrenFeatures() == null ) return;
        Iterator<Map.Entry<String, FeatureSymbol>> it = element.getChildrenFeatures().entrySet().iterator();
        while (it.hasNext()) {
        	walk(it.next().getValue(), list);
        }
    }
}
