package persistence;

import org.json.JSONObject;

// Based on the code structure from DemoFile
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
