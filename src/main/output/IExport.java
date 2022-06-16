package main.output;

import org.json.JSONException;

/**
 * Interface for the Export
 *
 *  @author Lang
 *  @version 1.0
 */
public interface IExport {

    public void export(StringBuilder jsonTree, String aggregat) throws JSONException;

}
