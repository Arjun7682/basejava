package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.javawebinar.basejava.storage.serializer.JsonStreamSerializer;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapResumeStorageTest.class,
        MapUuidStorageTest.class,
        FileStorageTest.class,
        PathStorageTest.class,
        XmlPathStorageTest.class,
        JsonStreamSerializer.class
})

public class StartAllTests {
}
