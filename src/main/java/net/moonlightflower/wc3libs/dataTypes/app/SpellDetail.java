package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpellDetail extends Int {
	public final static SpellDetail LOW = new SpellDetail(0);
	public final static SpellDetail MEDIUM = new SpellDetail(1);
	public final static SpellDetail HIGH = new SpellDetail(2);
	
	private static Map<Integer, SpellDetail> _map;

	static {
		_map = new LinkedHashMap<>();
	}

	public SpellDetail(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static SpellDetail valueOf(int val) {
		return _map.get(val);
	}
	
	@Override
	public SpellDetail decode(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (Exception e) {
		}
		
		return null;
	}
}
