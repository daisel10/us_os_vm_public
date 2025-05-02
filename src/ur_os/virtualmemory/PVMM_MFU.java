/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package ur_os.virtualmemory;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author user
 */

public class PVMM_MFU extends ProcessVirtualMemoryManager {

    public PVMM_MFU() {
        type = ProcessVirtualMemoryManagerType.MFU;
    }

    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
        // Conjunto para mantener las últimas páginas cargadas, sin duplicados y en orden de acceso reciente
        Set<Integer> loadedPages = new LinkedHashSet<>();
        
        // Paso 1: Identificar las últimas 'loaded' páginas únicas en memoria
        for (int i = memoryAccesses.size() - 1; i >= 0 && loadedPages.size() < loaded; i--) {
            int page = memoryAccesses.get(i);
            if (!loadedPages.contains(page)) {
                loadedPages.add(page);
            }
        }

        // Paso 2: Contar la frecuencia de acceso de cada página cargada
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int page : memoryAccesses) {
            if (loadedPages.contains(page)) {
                frequency.put(page, frequency.getOrDefault(page, 0) + 1);
            }
        }

        // Paso 3: Buscar la página con la mayor frecuencia de acceso
        int victim = -1;
        int maxFrequency = -1;
        for (int page : loadedPages) {
            int freq = frequency.getOrDefault(page, 0);
            if (freq > maxFrequency) {
                maxFrequency = freq;
                victim = page;
            }
        }
        
        return victim;
    }
}

