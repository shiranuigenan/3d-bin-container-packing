package com.github.skjolberg.packing;

import com.github.skjolberg.packing.impl.Level;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractPackagerTest {


	static void validate(Container pack) {
		for(Level level : pack.getLevels()) {
			level.validate();
		}
	}

	static void print(Container fits) {
		System.out.println();
		System.out.println(Visualizer.visualize(fits, 90, 2));
		System.out.println();
	}

	void runsLimitedTimeSeconds(Packager bruteForcePackager, long duration) {
		List<Box> containers = new ArrayList<>();
		containers.add(new Box(5000, 1000, 1000, 0));

		List<BoxItem> products1 = new ArrayList<>();

		for(int i = 0; i < 10000; i++) {
			Box box = new Box(Integer.toString(i), 5, 10, 10, 0);
			for(int k = 0; k < i % 2; k++) {
				box.rotate3D();
			}
			products1.add(new BoxItem(box, 1));
		}

		long time = System.currentTimeMillis();
		Container fits1 = bruteForcePackager.pack(products1, time + duration);
		assertNull(fits1);

		assertTrue("Used " + (System.currentTimeMillis() - time), System.currentTimeMillis() - time >= duration);
		assertTrue("Used " + (System.currentTimeMillis() - time), System.currentTimeMillis() - time <= duration + 1000);
	}



}
