/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class BestFitMemorySlotManager extends FreeMemorySlotManager{
    
    public BestFitMemorySlotManager(int memSize){
        super(memSize);
    }
    
    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot bestFit = null;

        
        for (MemorySlot memorySlot : list) {
            if (memorySlot.canContain(size)) {
                if (bestFit == null || memorySlot.getSize() < bestFit.getSize()) {
                    bestFit = memorySlot;
                }
            }
        }

        if (bestFit != null) {
            if (bestFit.getSize() == size) {        
                list.remove(bestFit);
                return bestFit;
            } else {
                
                return bestFit.assignMemory(size);
            }
        }

        
        System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
        return null;
    }
    
}
