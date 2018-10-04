package jdbc.lesson4.homework4_1.model;

import java.util.Objects;

public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    public File() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    public long getSize() {
        return size;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(name, file.name) &&
                Objects.equals(format, file.format) &&
                Objects.equals(storage, file.storage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, format, storage);
    }
}
