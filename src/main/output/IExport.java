package main.output;

import org.json.JSONException;

/**
 * Interface for the Export
 *
 */
public interface IExport {

    public void export(StringBuilder jsonTree, String aggregat) throws JSONException;

}
