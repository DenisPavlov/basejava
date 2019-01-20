package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    public void clear() {
        Arrays.fill(storage,0, size(),null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index >= 0) {
            storage[index] = resume;
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if(index < 0) {
            System.out.println(-index + "|" + ((-index)+1)+ "|" + (size+index));
            System.arraycopy(storage, -index, storage, (-index)+1, size+index);
            storage[-index] = resume;
            size++;
        }

    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if(index > 0) {
            System.arraycopy(storage, index+1, storage, index, size-index);
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        System.arraycopy(storage, 0, resume, 0, size);
        return resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}