package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

public class ItemId extends ObjId {
	protected ItemId(String idString) {
		super(idString);
	}

	public static ItemId valueOf(Id val) {
		return new ItemId(val.toString());
	}
}
