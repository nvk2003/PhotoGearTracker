package persistence;

import org.json.JSONObject;

// Based on the code structure from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
