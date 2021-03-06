package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.BinStream.StreamException;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * info file for wrapping war3map.w3i
 */
public class W3I {
    public final static File GAME_PATH = new File("war3map.w3i");

    private int _fileVersion;

    public int getFileVersion() {
        return _fileVersion;
    }

    public void setFileVersion(int _fileVersion) {
        this._fileVersion = _fileVersion;
    }

    private int _savesAmount = 0;

    public int getSavesAmount() {
        return _savesAmount;
    }

    public void setSavesAmount(int val) {
        _savesAmount = val;
    }

    private int _editorVersion = 0;

    public int getEditorVersion() {
        return _editorVersion;
    }

    public void setEditorVersion(int val) {
        _editorVersion = val;
    }

    private String _mapName;

    public String getMapName() {
        return _mapName;
    }

    public void setMapName(String val) {
        _mapName = val;
    }

    private String _mapAuthor;

    public String getMapAuthor() {
        return _mapAuthor;
    }

    public void setMapAuthor(String val) {
        _mapAuthor = val;
    }

    private String _mapDescription;

    public String getMapDescription() {
        return _mapDescription;
    }

    public void setMapDescription(String val) {
        _mapDescription = val;
    }

    private String _playersRecommendedAmount;

    public String getPlayersRecommendedAmount() {
        return _playersRecommendedAmount;
    }

    public void setPlayersRecommendedAmount(String val) {
        _playersRecommendedAmount = val;
    }

    private Coords2DF _cameraBounds1 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds2 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds3 = new Coords2DF(0F, 0F);
    private Coords2DF _cameraBounds4 = new Coords2DF(0F, 0F);

    public Coords2DF getCameraBounds1() {
        return _cameraBounds1;
    }

    public Coords2DF getCameraBounds2() {
        return _cameraBounds2;
    }

    public Coords2DF getCameraBounds3() {
        return _cameraBounds3;
    }

    public Coords2DF getCameraBounds4() {
        return _cameraBounds4;
    }

    public void setCameraBounds(Coords2DF pos1, Coords2DF pos2, Coords2DF pos3, Coords2DF pos4) {
        _cameraBounds1 = pos1;
        _cameraBounds2 = pos2;
        _cameraBounds3 = pos3;
        _cameraBounds4 = pos4;
    }

    private int _marginLeft = 0;
    private int _marginRight = 0;
    private int _marginBottom = 0;
    private int _marginTop = 0;

    public int getMarginLeft() {
        return _marginLeft;
    }

    public int getMarginRight() {
        return _marginRight;
    }

    public int getMarginBottom() {
        return _marginBottom;
    }

    public int getMarginTop() {
        return _marginTop;
    }

    public void setMargins(int left, int right, int bottom, int top) {
        _marginLeft = left;
        _marginRight = right;
        _marginBottom = bottom;
        _marginTop = top;
    }

    private int _width = 32;
    private int _height = 32;

    //without boundaries
    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public Bounds getWorldBounds() {
        Size size = new Size(getWidth() + getMarginLeft() + getMarginRight(), getHeight() + getMarginTop() + getMarginBottom());

        return new Bounds(size, new Coords2DI(0, 0));
    }

    public void setDimensions(int width, int height) {
        _width = width;
        _height = height;
    }

    public static class Flags extends FlagsInt {
        @Override
        public DataType decode(Object val) {
            // TODO
            return null;
        }

        @Override
        public Object toSLKVal() {
            // TODO
            return null;
        }

        @Override
        public Object toTXTVal() {
            // TODO
            return null;
        }

        public static class Flag extends FlagsInt.Flag {
            private static List<Flag> _all = new ArrayList<>();

            public final static Flag HIDE_MINIMAP = new Flag(0, "hideMinimap");
            public final static Flag MODIFY_ALLY_PRIORITIES = new Flag(1, "modifyAllyPriorities");
            public final static Flag MELEE_MAP = new Flag(2, "meleeMap");
            public final static Flag INITIAL_MAP_SIZE_LARGE_NEVER_MODIFIED = new Flag(3, "initialMapSizeLargeNeverModified");
            public final static Flag MASKED_AREAS_PARTIALLY_VISIBLE = new Flag(4, "maskedAreasPartiallyVisible");
            public final static Flag FIXED_PLAYER_FORCE_SETTING = new Flag(5, "fixedPlayerForceSetting");
            public final static Flag USE_CUSTOM_FORCES = new Flag(6, "useCustomForces");
            public final static Flag USE_CUSTOM_TECHS = new Flag(7, "useCustomTechs");
            public final static Flag USE_CUSTOM_ABILS = new Flag(8, "useCustomAbils");
            public final static Flag USE_CUSTOM_UPGRADES = new Flag(9, "useCustomUpgrades");
            public final static Flag MAP_PROPERTIES_WINDOW_OPENED_BEFORE = new Flag(10, "mapPrioritiesWindowOpenedBefore");
            public final static Flag SHOW_WATER_WAVES_ON_CLIFF_SHORES = new Flag(11, "showWaterWavesOnCliffShores");
            public final static Flag SHOW_WATER_WAVES_ON_ROLLING_SHORES = new Flag(12, "showWaterWavesOnRollingShores");
            public final static Flag UNKNOWN = new Flag(13, "unknown");
            public final static Flag UNKNOWN_B = new Flag(14, "unknownB");
            public final static Flag UNKNOWN_C = new Flag(15, "unknownC");

            public static Flag[] values() {
                Flag[] flags = new Flag[_all.size()];

                return _all.toArray(flags);
            }

            private Flag(int pos, String label) {
                super(pos, label);

                _all.add(this);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");

            Flag[] flags = Flag.values();

            if (flags.length > 0) {
                for (Flag flag : flags) {
                    if (sb.length() > 0) {
                        sb.append(" ");
                    }

                    sb.append(String.format("%s=%s", flag.toString(), containsFlag(flag)));
                }
            }

            return String.format("%s [%s]", Integer.toBinaryString(toInt()), sb.toString());
        }

        protected Flags(int val) {
            super(val);
        }

        public static Flags valueOf(int val) {
            return new Flags(val);
        }
    }

    private Flags _flags = Flags.valueOf(0);

    public Flags getFlags() {
        return _flags;
    }

    public void setFlags(Flags val) {
        _flags = val;
    }

    public boolean getFlag(Flags.Flag flag) {
        return _flags.containsFlag(flag);
    }

    public void setFlag(Flags.Flag flag, boolean val) {
        _flags.setFlag(flag, val);
    }

    private Tileset _tileset;

    public Tileset getTileset() {
        return _tileset;
    }

    public void setTileset(Tileset val) {
        _tileset = val;
    }

    public static class LoadingScreen {
        private LoadingScreenBackground _background;

        public LoadingScreenBackground getBackground() {
            return _background;
        }

        public void setBackground(LoadingScreenBackground val) {
            _background = val;
        }

        private String _text;

        public String getText() {
            return _text;
        }

        public void setText(String val) {
            _text = val;
        }

        private String _title;

        public String getTitle() {
            return _title;
        }

        public void setTitle(String val) {
            _title = val;
        }

        private String _subtitle;

        public String getSubtitle() {
            return _subtitle;
        }

        public void setSubtitle(String val) {
            _subtitle = val;
        }

        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        @Override
        public String toString() {
            return String.format("background=%s text=%s title=%s subtitle=%s index=%d", getBackground(), getText(), getTitle(), getSubtitle(), getIndex());
        }

        private void set(int backgroundIndex, String customPath, String text, String title, String subtitle, int index) {
            LoadingScreenBackground background = null;

            if (backgroundIndex < 0) {
                if ((customPath != null) && !customPath.isEmpty()) {
                    background = new LoadingScreenBackground.CustomBackground(new File(customPath));
                }
            } else {
                background = LoadingScreenBackground.PresetBackground.valueOf(backgroundIndex);
            }

            setBackground(background);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
            setIndex(index);
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            set(
                    stream.readInt("campaignBackgroundIndex"),
                    null,
                    stream.readString("loadingScreenText"),
                    stream.readString("loadingScreenTitle"),
                    stream.readString("loadingScreenSubtitle"),
                    stream.readInt("loadingScreenIndex")
            );
        }

        private void write_0x12(Wc3BinStream stream) {
            LoadingScreenBackground background = getBackground();

            stream.writeInt(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background).getIndex
                    () : -1);
            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
            stream.writeInt(getIndex());
        }

        private void read_0x19(Wc3BinStream stream) throws StreamException {
            set(
                    stream.readInt("campaignBackgroundIndex"),
                    stream.readString("loadingScreenModel"),
                    stream.readString("loadingScreenText"),
                    stream.readString("loadingScreenTitle"),
                    stream.readString("loadingScreenSubtitle"),
                    -1
            );
        }

        private void write_0x19(Wc3BinStream stream) {
            LoadingScreenBackground background = getBackground();

            if (background != null) {
                stream.writeInt(background instanceof LoadingScreenBackground.PresetBackground ? ((LoadingScreenBackground.PresetBackground) background)
                        .getIndex() : -1);
                stream.writeString(background instanceof LoadingScreenBackground.CustomBackground ? ((LoadingScreenBackground.CustomBackground) background)
                        .getCustomPath().toString() : null);
            } else {
                stream.writeInt(-1);
                stream.writeString((String) null);
            }

            stream.writeString(getText());
            stream.writeString(getTitle());
            stream.writeString(getSubtitle());
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19: {
                    write_0x19(stream);

                    break;
                }
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public LoadingScreen(LoadingScreenBackground background, String text, String title, String subtitle, int index) {
            setBackground(background);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
            setIndex(index);
        }

        public LoadingScreen(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }
    }

    private LoadingScreen _loadingScreen;

    public LoadingScreen getLoadingScreen() {
        return _loadingScreen;
    }

    public void setLoadingScreen(LoadingScreen val) {
        _loadingScreen = val;
    }

    public class PrologueScreen {
        private String _path;

        public String getPath() {
            return _path;
        }

        public void setPath(String val) {
            _path = val;
        }

        private String _text;

        public String getText() {
            return _text;
        }

        public void setText(String val) {
            _text = val;
        }

        private String _title;

        public String getTitle() {
            return _title;
        }

        public void setTitle(String val) {
            _title = val;
        }

        private String _subtitle;

        public String getSubtitle() {
            return _subtitle;
        }

        public void setSubtitle(String val) {
            _subtitle = val;
        }

        @Override
        public String toString() {
            return String.format("path=%s text=%s title=%s subtitle=%s", getPath(), getText(), getTitle(), getSubtitle());
        }

        public PrologueScreen(String path, String text, String title, String subtitle) {
            setPath(path);
            setText(text);
            setTitle(title);
            setSubtitle(subtitle);
        }
    }

    private PrologueScreen _prologueScreen;

    public PrologueScreen getPrologueScreen() {
        return _prologueScreen;
    }

    public void setPrologueScreen(PrologueScreen val) {
        _prologueScreen = val;
    }

    private String _loadingScreenModel;

    public String getLoadingScreenModel() {
        return _loadingScreenModel;
    }

    public void setLoadingScreenModel(String val) {
        _loadingScreenModel = val;
    }

    public static class GameDataSet {
        private static Map<Integer, GameDataSet> _map = new LinkedHashMap<>();

        public final static GameDataSet STANDARD = new GameDataSet(0, "DEFAULT");
        public final static GameDataSet CUSTOM_V1 = new GameDataSet(1, "CUSTOM_V1");
        public final static GameDataSet MELEE_V1 = new GameDataSet(2, "MELEE_V1");

        private int _index;

        public int getIndex() {
            return _index;
        }

        public static GameDataSet valueOf(int index) {
            return _map.get(index);
        }

        private String _label;

        public String getLabel() {
            return _label;
        }

        @Override
        public String toString() {
            return _label;
        }

        protected GameDataSet(int index, String label) {
            _map.put(index, this);

            _index = index;
            _label = String.format("WESTRING_GAMEDATASET_%s", label);
        }
    }

    private GameDataSet _gameData;

    public GameDataSet getGameDataSet() {
        return _gameData;
    }

    public void setGameDataSet(GameDataSet val) {
        _gameData = val;
    }

    private TerrainFog _terrainFog;

    public TerrainFog getTerrainFog() {
        return _terrainFog;
    }

    public void setTerrainFog(TerrainFog val) {
        _terrainFog = val;
    }

    private Id _globalWeatherId;

    public Id getGlobalWeatherId() {
        return _globalWeatherId;
    }

    public void setGlobalWeatherId(Id val) {
        _globalWeatherId = val;
    }

    private String _soundEnv;

    public String getSoundEnv() {
        return _soundEnv;
    }

    public void setSoundEnv(String val) {
        _soundEnv = val;
    }

    private char _tilesetLightEnv;

    public char getTilesetLightEnv() {
        return _tilesetLightEnv;
    }

    public void setTilesetLightEnv(char val) {
        _tilesetLightEnv = val;
    }

    private Color _waterColor = Color.fromRGBA(255, 255, 255, 255);

    public Color getWaterColor() {
        return _waterColor;
    }

    public void setWaterColor(Color val) {
        _waterColor = val;
    }

    public static class Player {
        private int _num = 0;

        public int getNum() {
            return _num;
        }

        public void setNum(int val) {
            _num = val;
        }

        private Controller _type = Controller.HUMAN;

        public Controller getType() {
            return _type;
        }

        public void setType(Controller val) {
            _type = val;
        }

        public static class UnitRace extends Int {
            private static Map<Integer, UnitRace> _map = new LinkedHashMap<>();

            public final static UnitRace NIGHT_ELF = new UnitRace(4, "NIGHT_ELF");
            public final static UnitRace HUMAN = new UnitRace(1, "HUMAN");
            public final static UnitRace ORC = new UnitRace(2, "ORC");
            public final static UnitRace SELECTABLE = new UnitRace(0, "SELECTABLE");
            public final static UnitRace UNDEAD = new UnitRace(3, "UNDEAD");

            private String _label;

            public String getLabel() {
                return _label;
            }

            @Override
            public String toString() {
                return getLabel();
            }

            public UnitRace(int val, String label) {
                super(val);

                _map.put(val, this);

                _label = label;
            }

            public static UnitRace valueOf(Integer val) {
                return _map.get(val);
            }

			/*public static UnitRace decodeStatic(Object val) {
				try {
					return valueOf(Integer.parseInt(val.toString()));
				} catch (NumberFormatException e) {
				}
				
				return null;
			}*/
        }

        private UnitRace _race = UnitRace.HUMAN;

        public UnitRace getRace() {
            return _race;
        }

        public void setRace(UnitRace val) {
            _race = val;
        }

        private int _startPosFixed = 0;

        public int getStartPosFixed() {
            return _startPosFixed;
        }

        public void setStartPosFixed(int val) {
            _startPosFixed = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        private Coords2DF _startPos = new Coords2DF(0F, 0F);

        public Coords2DF getStartPos() {
            return _startPos;
        }

        public void setStartPos(Coords2DF val) {
            _startPos = val;
        }

        public static class AllyFlags extends FlagsInt {
            private AllyFlags(int val) {
                super(val);
            }

            public static AllyFlags valueOf(int val) {
                return new AllyFlags(val);
            }

            @Override
            public DataType decode(Object val) {
                // TODO
                return null;
            }

            @Override
            public Object toSLKVal() {
                // TODO
                return null;
            }

            @Override
            public Object toTXTVal() {
                // TODO
                return null;
            }
        }

        private FlagsInt _allyLowPrioFlags = AllyFlags.valueOf(0);

        public int getAllyLowPrioFlags() {
            return _allyLowPrioFlags.toInt();
        }

        public void setAllyLowPrioFlags(int val) {
            _allyLowPrioFlags.setVal(val);
        }

        public void setAllyLowPrioFlag(int index, boolean val) {
            _allyLowPrioFlags.setPos(index, val);
        }

        private FlagsInt _allyHighPrioFlags = AllyFlags.valueOf(0);

        public int getAllyHighPrioFlags() {
            return _allyHighPrioFlags.toInt();
        }

        public void setAllyHighPrioFlags(int val) {
            _allyHighPrioFlags.setVal(val);
        }

        public void setAllyHighPrioFlag(int index, boolean val) {
            _allyHighPrioFlags.setPos(index, val);
        }

        @Override
        public String toString() {
            String allyLowPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyLowPrioFlags()));
            String allyHighPrioFlagsS = String.format("%12s", Integer.toBinaryString(getAllyHighPrioFlags()));

            allyLowPrioFlagsS = allyLowPrioFlagsS.substring(allyLowPrioFlagsS.length() - 12).replaceAll(" ", "0");
            allyHighPrioFlagsS = allyHighPrioFlagsS.substring(allyHighPrioFlagsS.length() - 12).replaceAll(" ", "0");

            return String.format("%s [num=%d controller=%s race=%s startPosFixed=%s startPos=%s allyLowPrioFlags=%s allyHighPrioFlags=%s]", getName(), getNum
                    (), getType(), getRace(), getStartPosFixed(), getStartPos(), allyLowPrioFlagsS, allyHighPrioFlagsS);
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            setNum(stream.readInt("playerNum"));

            setType(Controller.valueOf(stream.readInt("controller")));
            setRace(UnitRace.valueOf(stream.readInt("race")));

            setStartPosFixed(stream.readInt("startPosFixed"));

            setName(stream.readString("playerName"));

            setStartPos(new Coords2DF(stream.readFloat("startPosX"), stream.readFloat("startPosY")));

            setAllyLowPrioFlags(stream.readInt("allyLowPrioFlags"));
            setAllyHighPrioFlags(stream.readInt("allyHighPrioFlags"));
        }

        private void write_0x12(Wc3BinStream stream) {
            stream.writeInt(getNum());

            stream.writeInt(getType().getVal());
            stream.writeInt(getRace().getVal());

            stream.writeInt(getStartPosFixed());

            stream.writeString(getName());

            Coords2DF startPos = getStartPos();

            stream.writeFloat(startPos.getX());
            stream.writeFloat(startPos.getY());

            stream.writeInt(getAllyLowPrioFlags());
            stream.writeInt(getAllyHighPrioFlags());
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public Player(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public Player() {
        }
    }

    private List<Player> _players = new ArrayList<>();

    public List<Player> getPlayers() {
        return _players;
    }

    public Player getPlayerFromNum(int num) {
        for (Player p : _players) if (p.getNum() == num) return p;

        return null;
    }

    private void addPlayer(Player val) {
        _players.add(val);
    }

    public Player addPlayer() {
        Player player = new Player();

        addPlayer(player);

        return player;
    }

    public static class Force {
        static public class Flags extends FlagsInt {
            @Override
            public DataType decode(Object val) {
                // TODO
                return null;
            }

            @Override
            public Object toSLKVal() {
                // TODO
                return null;
            }

            @Override
            public Object toTXTVal() {
                // TODO
                return null;
            }

            public static class Flag extends FlagsInt.Flag {
                public final static Flag ALLIED = new Flag(0);
                public final static Flag ALLIED_VICTORY = new Flag(1);
                public final static Flag SHARED_VISION = new Flag(2);
                public final static Flag SHARED_UNIT_CONTROL = new Flag(4);
                public final static Flag SHARED_UNIT_CONTROL_ADVANCED = new Flag(5);

                private Flag(int pos) {
                    super(pos);
                }
            }

            @Override
            public String toString() {
                return String.format("%5s", Integer.toBinaryString(toInt())).replaceAll(" ", "0");
            }

            protected Flags(int val) {
                super(val);
            }

            static public Flags valueOf(int val) {
                return new Flags(val);
            }
        }

        private Flags _flags = Flags.valueOf(0);

        public Flags getFlags() {
            return _flags;
        }

        public void setFlags(Flags val) {
            _flags = val;
        }

        public boolean getFlag(Flags.Flag flag) {
            return _flags.containsFlag(flag);
        }

        public void setFlag(Flags.Flag flag, boolean val) {
            _flags.setFlag(flag, val);
        }

        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        @Override
        public String toString() {
            String playersS = String.format("%12s", Integer.toBinaryString(getPlayers()));

            playersS = playersS.substring(playersS.length() - 12).replaceAll(" ", "0");

            return String.format("%s [players=%s, flags=%s]", getName(), playersS, getFlags());
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            setFlags(Flags.valueOf(stream.readInt("forceFlags")));

            setPlayers(stream.readInt("forcePlayers"));

            setName(stream.readString("forceName"));
        }

        private void write_0x12(Wc3BinStream stream) {
            stream.writeInt(getFlags().toInt());

            stream.writeInt(getPlayers());

            stream.writeString(getName());
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public Force(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public Force() {
        }
    }

    private List<Force> _forces = new ArrayList<>();

    public List<Force> getForces() {
        return _forces;
    }

    private void addForce(Force val) {
        _forces.add(val);
    }

    public Force addForce() {
        Force force = new Force();

        addForce(force);

        return force;
    }

    public static class UpgradeMod {
        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
        }

        private Id _id;

        public Id getId() {
            return _id;
        }

        public void setId(Id val) {
            _id = val;
        }

        private int _level = 1;

        public int getLevel() {
            return _level;
        }

        public void setLevel(int val) {
            _level = val;
        }

        private int _avail = 0;

        public int getAvail() {
            return _avail;
        }

        public void setAvail(int val) {
            _avail = val;
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            setPlayers(stream.readInt("abilPlayers"));

            setId(stream.readId("abilId"));

            setLevel(stream.readInt("level"));

            setAvail(stream.readInt("avail"));
        }

        private void write_0x12(Wc3BinStream stream) {
            stream.writeInt(getPlayers());

            stream.writeId(getId());

            stream.writeInt(getLevel());

            stream.writeInt(getAvail());
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public UpgradeMod(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public UpgradeMod() {
        }
    }

    private List<UpgradeMod> _upgradeMods = new ArrayList<>();

    public List<UpgradeMod> getUpgradeMods() {
        return _upgradeMods;
    }

    private void addUpgradeMod(UpgradeMod val) {
        _upgradeMods.add(val);
    }

    public UpgradeMod addUpgradeMod() {
        UpgradeMod upgradeMod = new UpgradeMod();

        addUpgradeMod(upgradeMod);

        return upgradeMod;
    }

    public static class TechMod {
        private int _players = 0;

        public int getPlayers() {
            return _players;
        }

        public void setPlayers(int val) {
            _players = val;
        }

        private Id _id;

        public Id getId() {
            return _id;
        }

        public void setId(Id val) {
            _id = val;
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            setPlayers(stream.readInt("techPlayers"));

            setId(stream.readId("techId"));
        }

        private void write_0x12(Wc3BinStream stream) {
            stream.writeInt(getPlayers());

            stream.writeId(getId());
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public TechMod(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public TechMod() {
        }
    }

    private List<TechMod> _techMods = new ArrayList<>();

    public List<TechMod> getTechMods() {
        return _techMods;
    }

    private void addTechMod(TechMod val) {
        _techMods.add(val);
    }

    public TechMod addTechMod() {
        TechMod techMod = new TechMod();

        addTechMod(techMod);

        return techMod;
    }

    public static class UnitTable {
        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        enum PositionType {
            UNIT(0),
            STRUCTURE(1),
            ITEM(2);

            private int _val;

            public int getVal() {
                return _val;
            }

            private static Map<Integer, PositionType> _valToPositionTypeMap = new LinkedHashMap<>();

            PositionType(int val) {
                _val = val;
            }

            public static PositionType valueOf(int val) {
                return _valToPositionTypeMap.get(val);
            }

            static {
                _valToPositionTypeMap.put(0, UNIT);
            }
        }

        private Map<Integer, PositionType> _positionTypes = new LinkedHashMap<>();

        public PositionType getPositionType(int index) {
            return _positionTypes.get(index);
        }

        public void setPositionType(int index, PositionType val) {
            _positionTypes.put(index, val);
        }

        public static class Set {
            private UnitTable _parent;

            private int _chance = 100;

            public int getChance() {
                return _chance;
            }

            public void setChance(int val) {
                _chance = val;
            }

            private Map<Integer, Id> _typeIds = new LinkedHashMap<>();

            public Id getTypeId(int pos) {
                return _typeIds.get(pos);
            }

            public void setTypeId(int pos, Id val) {
                _typeIds.put(pos, val);
            }

            private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
                setChance(stream.readInt("chance"));

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    setTypeId(i, stream.readId("typeId"));
                }
            }

            private void write_0x12(Wc3BinStream stream) {
                stream.writeInt(getChance());

                for (int i = 0; i < _parent._positionTypes.size(); i++) {
                    stream.writeId(getTypeId(i));
                }
            }

            private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
                switch (format.toEnum()) {
                    case W3I_0x19:
                    case W3I_0x12: {
                        read_0x12(stream);

                        break;
                    }
                }
            }

            private void write(Wc3BinStream stream, EncodingFormat format) {
                switch (format.toEnum()) {
                    case AUTO:
                    case W3I_0x19:
                    case W3I_0x12: {
                        write_0x12(stream);

                        break;
                    }
                }
            }

            public Set(Wc3BinStream stream, EncodingFormat format, UnitTable parent) throws BinStream.StreamException {
                _parent = parent;

                read(stream, format);
            }

            public Set() {

            }
        }

        private List<Set> _sets = new ArrayList<>();

        private void addSet(Set val) {
            _sets.add(val);
        }

        public Set addSet() {
            Set set = new Set();

            addSet(set);

            return set;
        }

        private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
            setIndex(stream.readInt("index"));

            setName(stream.readString("name"));

            int positionsCount = stream.readInt("posCount");

            for (int i = 0; i < positionsCount; i++) {
                setPositionType(i, PositionType.valueOf(stream.readInt("posType")));
            }

            int setsCount = stream.readInt("setsCount");

            for (int i = 0; i < setsCount; i++) {
                addSet(new Set(stream, EncodingFormat.W3I_0x19, this));
            }
        }

        private void write_0x12(Wc3BinStream stream) {
            int positionsCount = _positionTypes.size();

            stream.writeInt(positionsCount);

            for (int i = 0; i < positionsCount; i++) {
                stream.writeInt(getPositionType(i).getVal());
            }

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19:
                case W3I_0x12: {
                    read_0x12(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19:
                case W3I_0x12: {
                    write_0x12(stream);

                    break;
                }
            }
        }

        public UnitTable(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public UnitTable() {
        }
    }

    private List<UnitTable> _unitTables = new ArrayList<>();

    public List<UnitTable> getUnitTables() {
        return _unitTables;
    }

    private void addUnitTable(UnitTable val) {
        _unitTables.add(val);
    }

    public UnitTable addUnitTable() {
        UnitTable unitTable = new UnitTable();

        addUnitTable(unitTable);

        return unitTable;
    }

    public static class ItemTable {
        private int _index = 0;

        public int getIndex() {
            return _index;
        }

        public void setIndex(int val) {
            _index = val;
        }

        private String _name;

        public String getName() {
            return _name;
        }

        public void setName(String val) {
            _name = val;
        }

        public static class Set {
            private int _chance = 100;

            public int getChance() {
                return _chance;
            }

            public void setChance(int val) {
                _chance = val;
            }

            private Id _id;

            public Id getTypeId() {
                return _id;
            }

            public void setTypeId(Id val) {
                _id = val;
            }

            private void read_0x19(Wc3BinStream stream) throws BinStream.StreamException {
                setChance(stream.readInt("chance"));

                setTypeId(stream.readId("typeId"));
            }

            private void write_0x19(Wc3BinStream stream) {
                stream.writeInt(getChance());

                stream.writeId(getTypeId());
            }

            private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
                switch (format.toEnum()) {
                    case W3I_0x19: {
                        read_0x19(stream);

                        break;
                    }
                }
            }

            private void write(Wc3BinStream stream, EncodingFormat format) {
                switch (format.toEnum()) {
                    case AUTO:
                    case W3I_0x19: {
                        write_0x19(stream);

                        break;
                    }
                }
            }

            public Set(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
                read(stream, format);
            }

            public Set() {
            }
        }

        private List<Set> _sets = new ArrayList<>();

        private void addSet(Set val) {
            _sets.add(val);
        }

        public Set addSet() {
            Set set = new Set();

            addSet(set);

            return set;
        }

        private void read_0x19(Wc3BinStream stream) throws BinStream.StreamException {
            setIndex(stream.readInt("index"));

            setName(stream.readString("name"));

            int setsCount = stream.readInt("setsCount");

            for (int i = 0; i < setsCount; i++) {
                addSet(new Set(stream, EncodingFormat.W3I_0x19));
            }
        }

        private void write_0x19(Wc3BinStream stream) {
            stream.writeInt(getIndex());

            stream.writeString(getName());

            for (Set set : _sets) {
                set.write(stream, EncodingFormat.W3I_0x19);
            }
        }

        private void read(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            switch (format.toEnum()) {
                case W3I_0x19: {
                    read_0x19(stream);

                    break;
                }
            }
        }

        private void write(Wc3BinStream stream, EncodingFormat format) {
            switch (format.toEnum()) {
                case AUTO:
                case W3I_0x19: {
                    write_0x19(stream);

                    break;
                }
            }
        }

        public ItemTable(Wc3BinStream stream, EncodingFormat format) throws BinStream.StreamException {
            read(stream, format);
        }

        public ItemTable() {
        }
    }

    private List<ItemTable> _itemTables = new ArrayList<>();

    public List<ItemTable> getItemTables() {
        return _itemTables;
    }

    private void addItemTable(ItemTable val) {
        _itemTables.add(val);
    }

    public ItemTable addItemTable() {
        ItemTable itemTable = new ItemTable();

        addItemTable(itemTable);

        return itemTable;
    }

    public void print(PrintStream outStream) {
        outStream.println(String.format("savesAmount: %d", getSavesAmount()));
        outStream.println(String.format("editorVersion: %d", getEditorVersion()));

        outStream.println(String.format("mapName: %s", getMapName()));
        outStream.println(String.format("mapAuthor: %s", getMapAuthor()));
        outStream.println(String.format("mapDescription: %s", getMapDescription()));
        outStream.println(String.format("playersRecommended: %s", getPlayersRecommendedAmount()));

        outStream.println(String.format("camBounds: [%s,%s,%s,%s]", getCameraBounds1(), getCameraBounds2(), getCameraBounds3(), getCameraBounds4()));
        outStream.println(String.format("margins: [left=%d right=%d bottom=%d top=%d]", getMarginLeft(), getMarginRight(), getMarginBottom(), getMarginTop()));
        outStream.println(String.format("dimensions: [width=%d height=%d]", getWidth(), getHeight()));

        outStream.println(String.format("flags: %s", getFlags()));
        outStream.println(String.format("tileset: %s", getTileset()));

        outStream.println(String.format("loadingScreen: %s", getLoadingScreen()));

        outStream.println(String.format("gameDataSet: %s", getGameDataSet()));

        outStream.println(String.format("prologueScreen: %s", getPrologueScreen()));

        outStream.println(String.format("terrainFog: %s", getTerrainFog()));

        outStream.println(String.format("globalWeatherId: %s", getGlobalWeatherId()));
        outStream.println(String.format("soundEnv: %s", getSoundEnv()));
        outStream.println(String.format("tilesetLightEnv: %s", getTilesetLightEnv()));

        outStream.println(String.format("waterColor: %s", getWaterColor()));
    }

    public void print() {
        print(System.out);
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        enum Enum {
            AUTO,
            W3I_0x19,
            W3I_0x12,
        }

        private static Map<Integer, EncodingFormat> _map = new LinkedHashMap<>();

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat W3I_0x19 = new EncodingFormat(Enum.W3I_0x19, 0x19);
        public final static EncodingFormat W3I_0x12 = new EncodingFormat(Enum.W3I_0x12, 0x12);

        public static EncodingFormat valueOf(int version) {
            return _map.get(version);
        }

        private EncodingFormat(Enum enumVal, int version) {
            super(enumVal, version);

            _map.put(version, this);
        }
    }

    private void read_0x12(Wc3BinStream stream) throws BinStream.StreamException {
        int version = stream.readInt("version");

        Wc3BinStream.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x12.getVersion(), version);

        setSavesAmount(stream.readInt("savesAmount"));
        setEditorVersion(stream.readInt("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(stream.readFloat("camA"), stream.readFloat("camB")), new Coords2DF(stream.readFloat("camC"), stream.readFloat("camD")),
                new Coords2DF(stream.readFloat("camE"), stream.readFloat("camF")), new Coords2DF(stream.readFloat("camG"), stream.readFloat("camH")));
        setMargins(stream.readInt("marginA"), stream.readInt("marginB"), stream.readInt("marginC"), stream.readInt("marginD"));

        setDimensions(stream.readInt("width"), stream.readInt("height"));

        setFlags(Flags.valueOf(stream.readInt("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x12));

        setPrologueScreen(new PrologueScreen(null, stream.readString("prologueScreenText"), stream.readString("prologueScreenTitle"), stream.readString
                ("prologueScreenSubtitle")));

        int playersCount = stream.readInt("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x12));
        }

        int forcesCount = stream.readInt("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x12));
        }

        int upgradeModsCount = stream.readInt("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x12));
        }

        int techModsCount = stream.readInt("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x12));
        }

        int unitTablesCount = stream.readInt("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x12));
        }
    }

    private void write_0x12(Wc3BinStream stream) {
        stream.writeInt(EncodingFormat.W3I_0x12.getVersion());

        stream.writeInt(getSavesAmount());
        stream.writeInt(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat(camBounds1.getX());
        stream.writeFloat(camBounds1.getY());
        stream.writeFloat(camBounds2.getX());
        stream.writeFloat(camBounds2.getY());
        stream.writeFloat(camBounds3.getX());
        stream.writeFloat(camBounds3.getY());
        stream.writeFloat(camBounds4.getX());
        stream.writeFloat(camBounds4.getY());

        stream.writeInt(getMarginLeft());
        stream.writeInt(getMarginRight());
        stream.writeInt(getMarginBottom());
        stream.writeInt(getMarginTop());

        stream.writeInt(getWidth());
        stream.writeInt(getHeight());

        stream.writeInt(getFlags().toInt());

        stream.writeChar(getTileset().getVal());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x12);

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen.getText());
        stream.writeString(prologueScreen.getTitle());
        stream.writeString(prologueScreen.getSubtitle());

        stream.writeInt(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x12);
        }

        stream.writeInt(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x12);
        }
    }

    private void read_0x19(Wc3BinStream stream) throws BinStream.StreamException {
        setFileVersion(stream.readInt("version"));

        Wc3BinStream.checkFormatVer("infoFileMaskFunc", EncodingFormat.W3I_0x19.getVersion(), getFileVersion());

        setSavesAmount(stream.readInt("savesAmount"));
        setEditorVersion(stream.readInt("editorVersion"));
        setMapName(stream.readString("mapName"));
        setMapAuthor(stream.readString("mapAuthor"));
        setMapDescription(stream.readString("mapDescription"));
        setPlayersRecommendedAmount(stream.readString("playersRecommendedAmount"));

        setCameraBounds(new Coords2DF(
                        stream.readFloat("camA"),
                        stream.readFloat("camB")),
                new Coords2DF(
                        stream.readFloat("camC"),
                        stream.readFloat("camD")),
                new Coords2DF(
                        stream.readFloat("camE"),
                        stream.readFloat("camF")),
                new Coords2DF(stream.readFloat("camG"),
                        stream.readFloat("camH")
                ));
        setMargins(stream.readInt("marginA"), stream.readInt("marginB"), stream.readInt("marginC"), stream.readInt("marginD"));

        setDimensions(stream.readInt("width"), stream.readInt("height"));

        setFlags(Flags.valueOf(stream.readInt("flags")));

        setTileset(Tileset.valueOf(stream.readChar("tileset")));

        setLoadingScreen(new LoadingScreen(stream, EncodingFormat.W3I_0x19));

        setGameDataSet(GameDataSet.valueOf(stream.readInt("gameData")));

        setPrologueScreen(new PrologueScreen(
                stream.readString("prologueScreenPath"),
                stream.readString("prologueScreenText"),
                stream.readString("prologueScreenTitle"),
                stream.readString("prologueScreenSubtitle")
        ));

        setTerrainFog(new TerrainFog(
                TerrainFogType.valueOf(stream.readInt("terrainFogType")),
                stream.readFloat("terrainFogZHeightStart"),
                stream.readFloat("terrainFogZHeightEnd"),
                stream.readFloat("terrainFogDensity"),
                Color.fromRGBA(
                        stream.readUByte("terrainFogRed"),
                        stream.readUByte("terrainFogGreen"),
                        stream.readUByte("terrainFogBlue"),
                        stream.readUByte("terrainFogAlpha"))
        ));

        setGlobalWeatherId(stream.readId("globalWeatherId"));
        setSoundEnv(stream.readString("soundEnv"));
        setTilesetLightEnv(stream.readChar("tilesetLightEnv"));

        setWaterColor(Color.fromRGBA(
                stream.readUByte("waterRed"),
                stream.readUByte("waterGreen"),
                stream.readUByte("waterBlue"),
                stream.readUByte("waterAlpha")
        ));

        int playersCount = stream.readInt("playersCount");

        for (int i = 0; i < playersCount; i++) {
            addPlayer(new Player(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int forcesCount = stream.readInt("forcesCount");

        for (int i = 0; i < forcesCount; i++) {
            addForce(new Force(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        if (stream.readByte() == 0xFF) return;

        stream.rewind(1);

        int upgradeModsCount = 0;

        upgradeModsCount = stream.readInt("upgradeModsCount");

        for (int i = 0; i < upgradeModsCount; i++) {
            addUpgradeMod(new UpgradeMod(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int techModsCount = stream.readInt("techModsCount");

        for (int i = 0; i < techModsCount; i++) {
            addTechMod(new TechMod(stream, EncodingFormat.W3I_0x19));
        }

        if (stream.eof()) return;

        int unitTablesCount = stream.readInt("unitTablesCount");

        for (int i = 0; i < unitTablesCount; i++) {
            addUnitTable(new UnitTable(stream, EncodingFormat.W3I_0x19));
        }
    }

    private void write_0x19(Wc3BinStream stream) {
        stream.writeInt(EncodingFormat.W3I_0x19.getVersion());

        stream.writeInt(getSavesAmount());
        stream.writeInt(getEditorVersion());
        stream.writeString(getMapName());
        stream.writeString(getMapAuthor());
        stream.writeString(getMapDescription());
        stream.writeString(getPlayersRecommendedAmount());

        Coords2DF camBounds1 = getCameraBounds1();
        Coords2DF camBounds2 = getCameraBounds2();
        Coords2DF camBounds3 = getCameraBounds3();
        Coords2DF camBounds4 = getCameraBounds4();

        stream.writeFloat(camBounds1.getX());
        stream.writeFloat(camBounds1.getY());
        stream.writeFloat(camBounds2.getX());
        stream.writeFloat(camBounds2.getY());
        stream.writeFloat(camBounds3.getX());
        stream.writeFloat(camBounds3.getY());
        stream.writeFloat(camBounds4.getX());
        stream.writeFloat(camBounds4.getY());

        stream.writeInt(getMarginLeft());
        stream.writeInt(getMarginRight());
        stream.writeInt(getMarginBottom());
        stream.writeInt(getMarginTop());

        stream.writeInt(getWidth());
        stream.writeInt(getHeight());

        stream.writeInt(getFlags().toInt());

        stream.writeChar(getTileset().getVal());

        getLoadingScreen().write(stream, EncodingFormat.W3I_0x19);

        stream.writeInt(getGameDataSet().getIndex());

        PrologueScreen prologueScreen = getPrologueScreen();

        stream.writeString(prologueScreen.getPath());
        stream.writeString(prologueScreen.getText());
        stream.writeString(prologueScreen.getTitle());
        stream.writeString(prologueScreen.getSubtitle());

        TerrainFog terrainFog = getTerrainFog();

        stream.writeInt(terrainFog.getType());
        stream.writeFloat(terrainFog.getZStart());
        stream.writeFloat(terrainFog.getZEnd());
        stream.writeFloat(terrainFog.getDensity());

        Color terrainFogColor = terrainFog.getColor();

        stream.writeUByte(terrainFogColor.getRed());
        stream.writeUByte(terrainFogColor.getGreen());
        stream.writeUByte(terrainFogColor.getBlue());
        stream.writeUByte(terrainFogColor.getAlpha());

        stream.writeId(getGlobalWeatherId());
        stream.writeString(getSoundEnv());
        stream.writeChar(getTilesetLightEnv());

        Color waterColor = getWaterColor();

        stream.writeUByte(waterColor.getRed());
        stream.writeUByte(waterColor.getGreen());
        stream.writeUByte(waterColor.getBlue());
        stream.writeUByte(waterColor.getAlpha());

        stream.writeInt(_players.size());

        for (Player player : _players) {
            player.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_forces.size());

        for (Force force : _forces) {
            force.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_upgradeMods.size());

        for (UpgradeMod upgradeMod : _upgradeMods) {
            upgradeMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_techMods.size());

        for (TechMod techMod : _techMods) {
            techMod.write(stream, EncodingFormat.W3I_0x19);
        }

        stream.writeInt(_unitTables.size());

        for (UnitTable unitTable : _unitTables) {
            unitTable.write(stream, EncodingFormat.W3I_0x19);
        }
    }

    private void read_auto(Wc3BinStream stream) throws Exception {
        int version = stream.readInt("version");
        System.out.println("Detected version: " + version);
        stream.rewind();

        read(stream, EncodingFormat.valueOf(version));
    }

    private void read(Wc3BinStream stream, EncodingFormat format) throws Exception {
        if (format == null) throw new Exception("no format");

        switch (format.toEnum()) {
            case AUTO:
                read_auto(stream);
                break;
            case W3I_0x12:
                read_0x12(stream);
                break;
            case W3I_0x19:
                read_0x19(stream);
                break;
        }
    }

    private void write(Wc3BinStream stream, EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
                EncodingFormat encodingFormat = EncodingFormat.valueOf(getFileVersion());
                write(stream, encodingFormat);
                break;
            case W3I_0x19: {
                write_0x19(stream);

                break;
            }
            case W3I_0x12: {
                write_0x12(stream);

                break;
            }
        }
    }

    private void read(Wc3BinStream stream) throws Exception {
        read(stream, EncodingFormat.AUTO);
    }

    private void write(Wc3BinStream stream) {
        write(stream, EncodingFormat.AUTO);
    }

    private void read(File file, EncodingFormat format) throws Exception {
        read(new Wc3BinStream(file), format);
    }

    private void write(File file, EncodingFormat format) throws IOException {
        write(new Wc3BinStream(file), format);
    }

    private void read(InputStream inStream) throws Exception {
        read(new Wc3BinStream(inStream), EncodingFormat.AUTO);
    }

    private void read(File file) throws Exception {
        read(file, EncodingFormat.AUTO);
    }

    public void write(File file) throws IOException {
        Wc3BinStream outStream = new Wc3BinStream();

        write(outStream);

        outStream.writeTo(file);
    }

    public W3I() {

    }

    public W3I(InputStream inStream) throws Exception {
        read(inStream);
    }

    public W3I(File inFile) throws Exception {
        InputStream inStream = new FileInputStream(inFile);

        read(inStream);

        inStream.close();
    }

    public static W3I ofMapFile(File mapFile) throws Exception {
        Orient.checkFileExists(mapFile);

        MpqPort.Out port = new JMpqPort.Out();

        port.add(GAME_PATH);

        MpqPort.Out.Result portResult = port.commit(mapFile);

        if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract info file");

        InputStream inStream = portResult.getInputStream(GAME_PATH);

        return new W3I(inStream);
    }

}
