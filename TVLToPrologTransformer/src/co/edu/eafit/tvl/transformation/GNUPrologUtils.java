package co.edu.eafit.tvl.transformation;

import java.util.Iterator;
import java.util.Map;

public class GNUPrologUtils {
	
	private GNUPrologUtils(){}
	
	public static String getVariableList(Map<Integer, String> variables) {
		StringBuilder sb = new StringBuilder("[");
		Iterator<Map.Entry<Integer, String>> it = variables.entrySet().iterator();
		if (it.hasNext()){
			sb.append(it.next().getValue());
		}
		while(it.hasNext()){
			Map.Entry<Integer, String> entry = it.next();
			sb.append(", ").append(entry.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
}
