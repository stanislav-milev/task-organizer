package org.btb.timer.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

/**
 * Utility class.
 * @author Stanislav Milev
 * @created on Mar 16, 2014
 */
public class Util {

	/**
	 * Returns an Image from the given file name. 
	 * @param fileName The file must be on the class path.
	 * @return image instance
	 */
	public static Image getImageFromFile(String fileName) {
		URL url = ClassLoader.getSystemResource(fileName);
		Toolkit kit = Toolkit.getDefaultToolkit();
		return kit.createImage(url);
	}

}
