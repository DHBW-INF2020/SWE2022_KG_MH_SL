package output;

import org.json.JSONException;

public interface IExport {

    public void export(StringBuilder jsonTree) throws JSONException;

}
