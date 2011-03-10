package com.example;

import java.io.File;
import java.io.FileFilter;

/** Filters file names using command-line wildcards.
 * <P>
 * <tt>*</tt> matches any number of characters.
 * <tt>?</tt> matches exactly one characters.
 * <P>
 * <b>Example:</b> 
 * <tt>*.txt</tt> matches all text files;  
 * <tt>stoya??.jpg</tt> matches all images that start
 * with <i>stoya</i> and got two more characters. 
 * 
 * @author Mich�le Wyss & Viviane Tanner
 *
 */
//DR Accepted! good version history and nice algorithm but try refactor methods out - try to watch out for code smells!
public class FilePattern implements FileFilter {
	
	private String pattern;

	public FilePattern(String string) {
		//DR you implemented the method - delete the comment!
		// TODO Auto-generated constructor stub
		pattern=string;
		
	}

	public boolean accept(File pathname) {
		
		String filename=pathname.getName();
		
		if(pattern.length()==0 && filename.length()==0)
			return true;
		if(pattern.length()==0 && filename.length()!=0)
			return false;
		if(pattern.equals("*") && filename.length()==0)
			return true;
		if(pattern.length()!=0 && filename.length()==0)
			return false;
		//if the first chars are the same cut them and compare the rest
		if(filename.charAt(0)==pattern.charAt(0)) {
			filename=filename.substring(1);
			pattern=pattern.substring(1);
			return accept(new File(filename));
		}
		
		/*DR Comments inside of methods smell! If you have to explain what a part of a method does
	       you should refactor by extracting the method (break it up into smaller methods)
	       method name is like a documentation, the outer method gets more readable
	       and method names don't go out of sync like comments do if you really have to comment write it as a method comment
	       */
		
		//if the pattern has a * first, check if the first char of
		//filename matches, if so cut the first char of both,
		//else only cut the one of filename, then compare again
		if(pattern.charAt(0)=='*'){
			if(pattern.length()!=1 && pattern.charAt(1)==filename.charAt(0))
				pattern=pattern.substring(2);
			filename=filename.substring(1);
			return accept(new File(filename));
		}
		
		if(pattern.charAt(0)=='?'){
			filename=filename.substring(1);
			pattern=pattern.substring(1);
			return accept(new File(filename));
		}
		else
			return false;
			
		//DR you implemented the method - delete the comment!
		// TODO Auto-generated method stub
	}
    
}