package com.masterdevskills.cha1.ext5;

public class DidThatWork {
    public static void main(String[] args) {
        Log logger = Logger.getLogger();

        logger.enableLogging();
        User user = new User("Bazlur", "Rahman");
        // logger.info("user status:  {}", getUserStatus(user));
        logger.info("user status:  {}", () -> new String[]{getUserStatus(user)});
        logger.trace("Tracking Id:  {}", () -> new String[]{getTrackingId()});
        logger.debug("Debug:  {}", () -> new String[]{getDebugResult()});
        logger.warn("show warn:  {}", () -> new String[]{showWarn()});
    }

    private static String getUserStatus(final User user) {
        System.out.println("Preparing user status");

        return user.toString();
    }
    private static String getTrackingId() {
        System.out.println("Inside Trace method");

        return "1234";
    }

    private static String getDebugResult() {
        System.out.println("Inside Debug method");

        return "Debug List";
    }

    private static String showWarn() {
        System.out.println("Inside warn method");

        return "warning state";
    }

    static class User {
        final String firstName;
        final String lastName;

        public User(final String firstName, final String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {

            return firstName + " " + lastName;
        }
    }
}
