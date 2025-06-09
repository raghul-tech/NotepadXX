package com.notepadxx.resources.icon;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

public class GetImage {

    public static Icon getImage(File file) { 
    	String ext = getFileExtension(file); 
        // Use a switch statement to determine the image to load 
    	try {
        switch (ext.toLowerCase()) {
           case"LICENSE": 
           case"license": 
           case"License": 
        	   return new ImageIcon(GetImage.class.getResource("License.png"));
        
            case "java": 
            case "jsp":
            	 return new ImageIcon(GetImage.class.getResource("java.png"));
            case "py":
            	 return new ImageIcon(GetImage.class.getResource("python.png"));
            case "c":
            case "C":
            	 return new ImageIcon(GetImage.class.getResource("C.png"));
            case "cpp":
            	 return new ImageIcon(GetImage.class.getResource("CPP.png"));
            case "cs":
            	 return new ImageIcon(GetImage.class.getResource("CSharp.png"));
            case "html":
            	 return new ImageIcon(GetImage.class.getResource("html.png"));
            case "css":
            	 return new ImageIcon(GetImage.class.getResource("css-3.png"));
            case "js":
            	 return new ImageIcon(GetImage.class.getResource("js.png"));
            case"ipynb":
            	 return new ImageIcon(GetImage.class.getResource("notebook.png"));
            case "xml":
            case"iml":
            	 return new ImageIcon(GetImage.class.getResource("xml.png"));
            case "yaml":
            case "yml":
            	 return new ImageIcon(GetImage.class.getResource("yaml.png"));
            case "json":
            	 return new ImageIcon(GetImage.class.getResource("json.png"));
            case "md":
            case "markdown":
            case "mdown":
            case "mkd":
            case "mkdn":
            case "mdtext":
            	return new ImageIcon(GetImage.class.getResource("MD.png"));
            case "php":
            	return new ImageIcon(GetImage.class.getResource("php.png"));
            case "sql":
            	return new ImageIcon(GetImage.class.getResource("sql.png"));
            case "sh":
            	return new ImageIcon(GetImage.class.getResource("bash.png"));
            case "rb":
            	return new ImageIcon(GetImage.class.getResource("ruby.png"));
            case "swift":
            	return new ImageIcon(GetImage.class.getResource("swift.png"));
            case "groovy":
            	return new ImageIcon(GetImage.class.getResource("groovy.png"));
            case "scala":
            	return new ImageIcon(GetImage.class.getResource("scala.png"));
            case "tex":
            	return new ImageIcon(GetImage.class.getResource("latex.png"));
            case "as":
            	return new ImageIcon(GetImage.class.getResource("actionscript.png"));
            case "asm":
            case "s":
            case "a51":
            case "a65":
            case "inc":
            	return new ImageIcon(GetImage.class.getResource("assemb.png"));
            case "csv":
            	return new ImageIcon(GetImage.class.getResource("csv.png"));
            case "d":
            	return new ImageIcon(GetImage.class.getResource("D (4).png"));
            case"dart":
            	return new ImageIcon(GetImage.class.getResource("dart.png"));
            case "pas":
            case "dpr":
            case "dfr":
            case "dpm":
            	return new ImageIcon(GetImage.class.getResource("delphi.png"));
            case"dockerfile":
            	return new ImageIcon(GetImage.class.getResource("docker.png"));
            case "clj":
            case "cljs":
            case "cljc":
            case "clojure":
            	return new ImageIcon(GetImage.class.getResource("clojure.png"));     
            case "go":
            	return new ImageIcon(GetImage.class.getResource("go.png"));
            case "hbs":
            case "handlebars":
            	return new ImageIcon(GetImage.class.getResource("handlebar.png"));
            case "kt":
            case "kts":
            	return new ImageIcon(GetImage.class.getResource("kotlin2.png"));
            case "less":
            	return new ImageIcon(GetImage.class.getResource("less.png"));
            case "lisp":	
            case "lsp":
            	return new ImageIcon(GetImage.class.getResource("lisp.png"));
            case "lua":
            	return new ImageIcon(GetImage.class.getResource("lua2.png"));
            case "pl":
            case "pm":
            case "t":
            case "cgi":
            	return new ImageIcon(GetImage.class.getResource("perl.png"));
            case "rs":
            	return new ImageIcon(GetImage.class.getResource("rust.png"));
            case "sas":
            case "sas7bdat":
            case "sas7bvew":
            case "sas7bcat":
            	return new ImageIcon(GetImage.class.getResource("sas.png"));
            case "tcl":
            	return new ImageIcon(GetImage.class.getResource("tcl.png"));
            case "ts":
            	return new ImageIcon(GetImage.class.getResource("typescript.png"));
            case "tsx":
            case "jsx":
            	return new ImageIcon(GetImage.class.getResource("react.png"));
          
            case "vb":
            case "vbs":
            case "bas":
            case "frm":
            case "cls":
            	return new ImageIcon(GetImage.class.getResource("visual-basic.png"));
            	
            default:
                return UIManager.getIcon("FileView.fileIcon"); // Return null if no match is found
            	//return null;
        }
    	}catch(Exception i) {
    		try {
    		return UIManager.getIcon("FileView.directoryIcon");
    		}catch(Error e) {
    		 return (ImageIcon)FileSystemView.getFileSystemView().getSystemIcon(file);
    		}
    		}
    }

    
    public static Icon getImage(String ext) { 
    	
    	try {
        switch (ext.toLowerCase()) {
           case"LICENSE": 
           case"license":
           case"License":
        	   return new ImageIcon(GetImage.class.getResource("License.png"));
        
            case "java":
            case "jsp":
            	 return new ImageIcon(GetImage.class.getResource("java.png"));
            case "py":
            	 return new ImageIcon(GetImage.class.getResource("python.png"));
            case "c":
            case "C":
            	 return new ImageIcon(GetImage.class.getResource("C.png"));
            case "cpp":
            	 return new ImageIcon(GetImage.class.getResource("CPP.png"));
            case "cs":
            	 return new ImageIcon(GetImage.class.getResource("CSharp.png"));
            case "html":
            	 return new ImageIcon(GetImage.class.getResource("html.png"));
            case "css":
            	 return new ImageIcon(GetImage.class.getResource("css-3.png"));
            case "js":
            	 return new ImageIcon(GetImage.class.getResource("js.png"));
            case"ipynb":
            	 return new ImageIcon(GetImage.class.getResource("notebook.png"));
            case "xml":
            case"iml":
            	 return new ImageIcon(GetImage.class.getResource("xml.png"));
            case "yaml":
            case "yml":
            	 return new ImageIcon(GetImage.class.getResource("yaml.png"));
            case "json":
            	 return new ImageIcon(GetImage.class.getResource("json.png"));
            case "md":
            case "markdown":
            case "mdown":
            case "mkd":
            case "mkdn":
            case "mdtext":
            	return new ImageIcon(GetImage.class.getResource("MD.png"));
            case "php":
            	return new ImageIcon(GetImage.class.getResource("php.png"));
            case "sql":
            	return new ImageIcon(GetImage.class.getResource("sql.png"));
            case "sh":
            	return new ImageIcon(GetImage.class.getResource("bash.png"));
            case "rb":
            	return new ImageIcon(GetImage.class.getResource("ruby.png"));
            case "swift":
            	return new ImageIcon(GetImage.class.getResource("swift.png"));
            case "groovy":
            	return new ImageIcon(GetImage.class.getResource("groovy.png"));
            case "scala":
            	return new ImageIcon(GetImage.class.getResource("scala.png"));
            case "tex":
            	return new ImageIcon(GetImage.class.getResource("latex.png"));
            case "as":
            	return new ImageIcon(GetImage.class.getResource("actionscript.png"));
            case "asm":
            case "s":
            case "a51":
            case "a65":
            case "inc":
            	return new ImageIcon(GetImage.class.getResource("assembly.png"));
            case "csv":
            	return new ImageIcon(GetImage.class.getResource("csv.png"));
            case "d":
            	return new ImageIcon(GetImage.class.getResource("D (4).png"));
            case"dart":
            	return new ImageIcon(GetImage.class.getResource("dart.png"));
            case "pas":
            case "dpr":
            case "dfr":
            case "dpm":
            	return new ImageIcon(GetImage.class.getResource("delphi.png"));
            case"dockerfile":
            	return new ImageIcon(GetImage.class.getResource("docker.png"));
            case "clj":
            case "cljs":
            case "cljc":
            case "clojure":
            	return new ImageIcon(GetImage.class.getResource("clojure.png"));     
            case "go":
            	return new ImageIcon(GetImage.class.getResource("go.png"));
            case "hbs":
            case "handlebars":
            	return new ImageIcon(GetImage.class.getResource("handlebar.png"));
            case "kt":
            case "kts":
            	return new ImageIcon(GetImage.class.getResource("kotlin2.png"));
            case "less":
            	return new ImageIcon(GetImage.class.getResource("less.png"));
            case "lisp":	
            case "lsp":
            	return new ImageIcon(GetImage.class.getResource("lisp.png"));
            case "lua":
            	return new ImageIcon(GetImage.class.getResource("lua2.png"));
            case "pl":
            case "pm":
            case "t":
            case "cgi":
            	return new ImageIcon(GetImage.class.getResource("perl.png"));
            case "rs":
            	return new ImageIcon(GetImage.class.getResource("rust.png"));
            case "sas":
            case "sas7bdat":
            case "sas7bvew":
            case "sas7bcat":
            	return new ImageIcon(GetImage.class.getResource("sas.png"));
            case "tcl":
            	return new ImageIcon(GetImage.class.getResource("tcl.png"));
            case "ts":
            	return new ImageIcon(GetImage.class.getResource("typescript.png"));
            case "tsx":
            case "jsx":
            	return new ImageIcon(GetImage.class.getResource("react.png"));
            case "vb":
            case "vbs":
            case "bas":
            case "frm":
            case "cls":
            	return new ImageIcon(GetImage.class.getResource("visual-basic.png"));
          
            default:
               //return null;
            	 return UIManager.getIcon("FileView.fileIcon"); // Return null if no match is found
        }
    	}catch(Exception i) {
    		try {
    		return UIManager.getIcon("FileView.directoryIcon");
    		}catch(Error e) {
       		 return null;
       		}
    		
    		}
    }

private static String getFileExtension(File file) {
	if(file== null) {
		return"";
	}
	
String fileName = file.getName();
if (fileName.toLowerCase().startsWith("dockerfile.")) {
    return "dockerfile"; // Return the full file name
}
if (fileName.equalsIgnoreCase("LICENSE") ) {
    return "LICENSE"; // Return the full file name
}


int dotIndex = fileName.lastIndexOf('.');
if (dotIndex >= 0) {
   return fileName.substring(dotIndex + 1);
} else {
   return fileName; // No extension

}
}
}