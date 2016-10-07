package com.two95.jaiseGitServices;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
/**
* remote uri is the URI of the github repository that is to be cloned as a local repository.
*local repopath is the file directory where the cloned repository is to be put
*/
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
