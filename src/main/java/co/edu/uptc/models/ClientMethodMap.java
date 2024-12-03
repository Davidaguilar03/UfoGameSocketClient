package co.edu.uptc.models;

import lombok.Getter;
import java.util.HashMap;

@Getter
public class ClientMethodMap {
    private UfoSocketClient client;
    private HashMap<String, MethodLibrary> map;

    public ClientMethodMap(UfoSocketClient client) {
        this.client = client;
        map = new HashMap<>();
        addToMap();
    }

    public void addToMap() {
        map.put("UPDATE_UFO_COUNT", inputLine -> client.updateUfoCount(inputLine));
        map.put("PLAY_CRASH_SOUND", inputLine -> client.playCrashSound());
        map.put("INCREMENT_CRASHED_UFO_COUNT", inputLine -> client.incrementCrashedUfoCount(inputLine));
        map.put("PLAY_LANDING_SOUND", inputLine -> client.playLandingSound());
        map.put("INCREMENT_LANDED_UFO_COUNT", inputLine -> client.incrementLandedUfoCount());
        map.put("UPDATE_CONNECTED_PLAYERS", inputLine -> client.updateConnectedPlayers(inputLine));
        map.put("UPDATE_UFOS", inputLine -> client.updateUfos());
        map.put("UFO_LIST", inputLine -> client.updateUfoList(inputLine));
        map.put("SELECTED_UFO", inputLine -> client.handleSelectedUfo(inputLine));
        map.put("FORCE_START_GAME", inputLine -> client.forceStartGame());
        map.put("UFO_IMAGE", inputLine -> client.updateUfoDesign(inputLine));
        map.put("SET_CLIENT_MODE", inputLine -> client.setClientMode());
        map.put("USERNAME_LIST", inputLine -> client.handleUsersList(inputLine));
        map.put("UPDATE_PLAYERS_LIST", inputLine -> client.updateUsernameList());
    }

    public void run(String key, String inputLine) {
        MethodLibrary method = map.get(key);
        if (method != null) {
            method.execute(inputLine);
        }
    }

    public interface MethodLibrary {
        void execute(String inputLine);
    }
}
