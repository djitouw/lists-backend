package com.djitouw.lists.backend.factories.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;

import com.djitouw.lists.backend.objects.Flag;
import com.djitouw.lists.backend.objects.URD;
import com.djitouw.lists.backend.provider.database.objects.URDDB;

/**
 * Abstract Factory class.
 * 
 * @author Djitouw
 *
 */
public abstract class AbstractFactory {

	private static final Logger LOGGER = Logger.getLogger(AbstractFactory.class);

	protected URD buildURDDB(URDDB urdDB) {
		URD urd = null;
		if (urdDB != null) {
			urd = new URD();
			List<String> flagsDB = urdDB.getFlags();
			if (CollectionUtils.isNotEmpty(flagsDB)) {
				List<Flag> flags = new ArrayList<>();
				for (String strFlag : flagsDB) {
					try {
						Flag flag = Flag.valueOf(strFlag);
						flags.add(flag);
					} catch (IllegalArgumentException e) {
						LOGGER.error("Unknown flag: " + strFlag);
					}
				}
				if (CollectionUtils.isNotEmpty(flags)) {
					urd.setFlags(flags);
				}
			}

		}
		return urd;
	}
}
