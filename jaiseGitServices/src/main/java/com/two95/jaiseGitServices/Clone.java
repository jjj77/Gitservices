package com.two95.jaiseGitServices;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class Clone {
	
	
	public static String gitClone(String remoteGitUrl, String localRepoPath){
		
		
		try {
			Git giti = Git.cloneRepository().setURI(remoteGitUrl)
					.setDirectory(new File(localRepoPath)).call();
			giti.close();
		} catch (InvalidRemoteException e) {

			e.printStackTrace();
		} catch (TransportException e) {

			e.printStackTrace();
		} catch (GitAPIException e) {

			e.printStackTrace();
		}
		System.out.println("Done");
		String localGitRepo = localRepoPath+"/.git";

		
		
		
		return localGitRepo;
	}

}
