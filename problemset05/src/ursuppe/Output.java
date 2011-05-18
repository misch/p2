package ursuppe;

/** Determines where the output will be printed. */


public abstract class Output {
	public static Output outputInstance;
	
	public static void println(String s){
		outputInstance.print(s+"\n");
	}

	public abstract void print(String s);

	public static void setOutput(Output output){
		outputInstance = output;
	}
	
}
