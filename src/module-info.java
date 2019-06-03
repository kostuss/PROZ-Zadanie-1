module org.openjfx {
	
	 	requires javafx.controls;
	    requires javafx.fxml;
	    requires transitive javafx.base;
	    requires transitive javafx.graphics;
	    requires jdk.jshell;
	    
	    opens org.openjfx to javafx.fxml; 
	    exports org.openjfx; 
}