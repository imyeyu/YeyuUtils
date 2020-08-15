package net.imyeyu.util;

import net.imyeyu.util.implement.EncodeImp;
import net.imyeyu.util.implement.IOImp;
import net.imyeyu.util.implement.GUIImp;
import net.imyeyu.util.implement.NetworkImp;
import net.imyeyu.util.implement.ToolsImp;
import net.imyeyu.util.interfaces.Encode;
import net.imyeyu.util.interfaces.IO;
import net.imyeyu.util.interfaces.GUI;
import net.imyeyu.util.interfaces.Network;
import net.imyeyu.util.interfaces.Tools;

/**
 * Main interface
 * 
 * @author Yeyu
 *
 */
public class YeyuUtils {
	
	public static final String RES_PATH = "/net/imyeyu/util/res/";
	
	/**
	 * Some en/de code function
	 * 
	 * @return Encode interface
	 */
	public static Encode encode() {
		return new EncodeImp();
	}
	
	/**
	 * Some file manager function
	 * Use of control disk file and application data 
	 * 
	 * @return File manager interface
	 */
	public static IO io() {
		return new IOImp();
	}
	
	/**
	 * Some GUI function
	 * In common use GUI
	 * 
	 * @return GUI interface
	 */
	public static GUI gui() {
		return new GUIImp();
	}
	
	/**
	 * Some network function
	 * 
	 * @return Network interface
	 */
	public static Network network() {
		return new NetworkImp();
	}
	
	/**
	 * Some tools function
	 * 
	 * @return tools interface
	 */
	public static Tools tools() {
		return new ToolsImp();
	}
}
