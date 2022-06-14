package main.output;

import org.json.JSONException;

public interface IExport {

    public void export(StringBuilder jsonTree, String aggregat) throws JSONException;

}
