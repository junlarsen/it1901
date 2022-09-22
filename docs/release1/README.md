# CardSnap Release #1

This is the initial version of CardSnap that is buildable and testable. The
application does not support any extensive features at the moment due to the
short sprint duration. Additionally, we planned for the release to contain only
the bare necessities to run an interactive JavaFX application.

Today you can launch the application and you will be greeted by a button and a
counter. By clicking the button you'll increment the counter, and at the 
same time it will save the state to disk. Upon opening the application again you
will continue where you left off.

The requirements for the release milestone have been fulfilled:

1. Maven is the tool that is used to build, test, and run the application.
2. All tasks during this release have been connected to the GitLab milestone.
3. Documentation is provided in README.md files in root and codebase 
   directories that describe the application.
4. GitLab issues have been used for tasks and user stories. Additionally we 
   have used GitLab labels on issues to represent priority, effort, and 
   issue type (task or user story).
5. The view layer has an interactive button that increments a counter, the 
   logic layer has an in-memory representation of a counter that is 
   incremental, and the persistence layer has an implementation that will 
   load and store counter state to files on disk.
6. GitPod has been configured for the application such that both developers 
   and testers can use GitPod as an environment to test the application.
