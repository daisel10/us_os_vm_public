/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.*;

/**
 *
 * @author user
 */

public class PVMM_LRU extends ProcessVirtualMemoryManager {

    public PVMM_LRU() {
        type = ProcessVirtualMemoryManagerType.LRU;
    }

    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
        Set<Integer> pages = new LinkedHashSet<>();
        for (int i = memoryAccesses.size() - 1; i >= 0 && pages.size() < loaded; i--)
            pages.add(memoryAccesses.get(i));
        List<Integer> list = new ArrayList<>(pages);
        return list.get(list.size() - 1); // menos reciente
    }
}

