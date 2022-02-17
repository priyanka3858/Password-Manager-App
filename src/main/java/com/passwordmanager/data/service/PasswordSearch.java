package com.passwordmanager.data.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PasswordSearch {

    public static class SearchPassword implements Runnable {
        private final String stringFilter;
        Date date = new Date();

        public SearchPassword(String s)
        {
            this.stringFilter = s;
        }

        public void run() {
            SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
            System.out.println(Thread.currentThread().getName()+" [Start] searchTerm = "+ stringFilter + " at "+time.format(date));

            //method to search the database
            searchToDatabase();

            System.out.println(Thread.currentThread().getName()+" [End] " + "at "+time.format(date));
        }

        private void searchToDatabase(){
            try {

                // search for the password in the SQL database.
                // This is a dummy search. simulates the search time.

                Thread.sleep(2000);

                // original code to execute the search in the database
                // search for the password in the SQL database.
                // PasswordManagerService service = new PasswordManagerService();
                // service.findAllPasswords(name);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void TestFixedPool() {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);//creating a pool of 3 threads


        List<String> searchTerms = List.of("search1", "search2", "search3", "search4", "search5");

        for (String searchTerm : searchTerms) {
            Runnable worker = new SearchPassword("" + searchTerm);

            threadPool.execute(worker);//calling execute method of ExecutorService
        }

        threadPool.shutdown();//shutdown the pool
    }

    public void TestDynamicPool() throws InterruptedException {

        ExecutorService threadPool = Executors.newCachedThreadPool();//creating a pool of dynamic threads

        List<String> searchTerms = List.of("search1", "search2", "search3", "search4", "search5");

        for (String searchTerm : searchTerms) {
            Runnable worker = new SearchPassword("" + searchTerm);

            threadPool.execute(worker);//calling execute method of ExecutorService
        }

        threadPool.awaitTermination(30, TimeUnit.SECONDS);//waiting for all threads to complete
    }
}

