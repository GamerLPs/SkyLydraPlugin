package de.Barryonixx.Main.report;

public enum ReportCause {
    FLYING,
    KILLAURA,
    HACKING,
    TEAMING,
    CHEATING,
    AUTOCLICK;

    public static String getReportCause(String cause){

        for(ReportCause rc : values()){
            if(cause.equalsIgnoreCase(rc.toString())){
                return rc.toString();
            }
        }
        return null;
    }
}