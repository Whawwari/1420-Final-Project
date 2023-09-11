package com.mycompany.sampleproject;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.nio.file.Paths;
import java.nio.file.Path;

class RemoteEntry extends Entry {
    private final String repoId;
    private final int entryId;
    private String name;
    private long length;
    private boolean isDirectory;

    public RemoteEntry(String repoId, int entryId) {
        this.repoId = repoId;
        this.entryId = entryId;
        //this.name = name;
        //this.length = length;
        //this.isDirectory = isDirectory;
    }
    public static String DownloadFile(String repoId, int entryId){
        String servicePrincipalKey = "1JZO3_goI87YZTY8q_X4";
        String accessKeyBase64 = "ewoJImN1c3RvbWVySWQiOiAiMTQwMTM1OTIzOCIsCgkiY2xpZW50SWQiOiAiZTExOTRmODAtMjcxOS00YzQ0LTk0MzUtZjVkNzU3OTk4Mjg2IiwKCSJkb21haW4iOiAibGFzZXJmaWNoZS5jYSIsCgkiandrIjogewoJCSJrdHkiOiAiRUMiLAoJCSJjcnYiOiAiUC0yNTYiLAoJCSJ1c2UiOiAic2lnIiwKCQkia2lkIjogIl92a2ZENUExRUpQUHNQVEJKZTd5ZXEyT1JDbFJLam1OSFhzRmxiSkVpdWciLAoJCSJ4IjogIll3aGNHZGR6Y3VxY29rMVp0WFdSaTlIN0MtVDF2Y2dfYmFlQXB3R25Za2MiLAoJCSJ5IjogImFGcXVMT2NGVjg1cl9yZF8yMnQ2Z1R6aHJCQTFWeEdGWUNwWk9OUTNORTgiLAoJCSJkIjogInM3aDdxeFZXR3FCZm93em5obTJUbXIyTVpQT0IzUmloVXl0NG41MTBzWjgiLAoJCSJpYXQiOiAxNjc3Mjk3Mzg3Cgl9Cn0=";
        repoId = "r-0001d410ba56";
        AccessKey accessKey = AccessKey.createFromBase64EncodedAccessKey(accessKeyBase64);
    RepositoryApiClient client = RepositoryApiClientImpl.createFromAccessKey(
                servicePrincipalKey, accessKey);  
    // Download a list of entries from Repo
      com.laserfiche.repository.api.clients.impl.model.Entry entry = client.getEntriesClient()
                .getEntry(repoId, entryId, null).join();
        final String FILE_NAME = entry.getName() + ".txt";
        Consumer<InputStream> consumer = inputStream -> {
            File exportedFile = new File(FILE_NAME);
            try (FileOutputStream outputStream = new FileOutputStream(exportedFile)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    int length = inputStream.read(buffer);
                    if (length == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        client.getEntriesClient()
                .exportDocument(repoId, entryId, null, consumer)
                .join();
        Path path = Paths.get(FILE_NAME);
           return path.toAbsolutePath().toString();  
    }  
    
    public int getEntryID(){
        return entryId;
    }
    @Override
    public String getName() {
        return name;
    }

    public long getLength() {
        return length;
    }
    
    public String getRepoId() {
        return repoId;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
