/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.virtualmemory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author user
 */
public class PVMM_LFU extends ProcessVirtualMemoryManager {

    public PVMM_LFU() {
        type = ProcessVirtualMemoryManagerType.LFU;
    }

    @Override
    public int getVictim(LinkedList<Integer> memoryAccesses, int loaded) {
        Map<Integer, Integer> frequency = new HashMap<>();
        LinkedList<Integer> loadedPages = new LinkedList<>();

        // Identificar las últimas 'loaded' páginas únicas que están en memoria
        for (int i = memoryAccesses.size() - 1; i >= 0 && loadedPages.size() < loaded; i--) {
            int page = memoryAccesses.get(i);
            if (!loadedPages.contains(page)) {
                loadedPages.addFirst(page); // Agrega en orden de aparición
            }
        }

        // Contar frecuencia de acceso para las páginas cargadas
        for (int page : memoryAccesses) {
            if (loadedPages.contains(page)) {
                frequency.put(page, frequency.getOrDefault(page, 0) + 1);
            }
        }

        // Seleccionar la página con menor frecuencia
        int victim = loadedPages.getFirst();
        int minFreq = frequency.get(victim);

        for (int page : loadedPages) {
            int freq = frequency.get(page);
            if (freq < minFreq) {
                minFreq = freq;
                victim = page;
            }
        }

        return victim;
    }
}
