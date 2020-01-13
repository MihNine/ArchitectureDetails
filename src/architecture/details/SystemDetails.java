package architecture.details;

import java.util.Arrays;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.VirtualMemory;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

public class SystemDetails {

	private SystemInfo systemInfo;
	private HardwareAbstractionLayer hardwareAbstractionLayer;
	private OperatingSystem operatingSystem;

	public SystemDetails() {
		systemInfo = new SystemInfo();
		hardwareAbstractionLayer = systemInfo.getHardware();
		operatingSystem = systemInfo.getOperatingSystem();
	}

	public String generalInfoProcessor() {
		
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();
		
		return "operation system : " + centralProcessor.getFamily() + "\n" + "logical processor count unit : "
				+ centralProcessor.getLogicalProcessorCount() + "\n" + "identifier : " + centralProcessor.getIdentifier() + "\n" + "name: " + centralProcessor.getName()
				+ " \n" + "model: " + centralProcessor.getModel() + "\n" + "max frequency : " + centralProcessor.getMaxFreq() + "\n" +  "vendor frequency: " + centralProcessor.getVendorFreq() + "\n"  
				+ "interrupts : " + centralProcessor.getInterrupts();
	}

	public String getDiskStoreDetails() {
		String details = "";
		HWDiskStore[] hwDiskStore = hardwareAbstractionLayer.getDiskStores();
		for (int i = 0; i < hwDiskStore.length; i++) {

			details += "Disk " + (i + 1) + " with name " + hwDiskStore[i].getName() + ", serial "
					+ hwDiskStore[i].getSerial() + ", size " + hwDiskStore[i].getSize() + ", transfer time "
					+ hwDiskStore[i].getTransferTime() + "\n";
			HWPartition[] hwPartition = hwDiskStore[i].getPartitions();

			for (int j = 0; j < hwPartition.length; j++) {
				details += "          " + "partition " + (j + 1) + ", name " + hwPartition[j].getName()
						+ ", mount point " + hwPartition[j].getMountPoint() + ", UUID " + hwPartition[j].getUuid()
						+ ", size " + hwPartition[j].getSize() + "\n";
			}
		}

		return details;
	}

	public String getMemoryDetails() {
		GlobalMemory globalMemory = hardwareAbstractionLayer.getMemory();
		String details = "Memory , total " + globalMemory.getTotal() + "from witch is available "
				+ globalMemory.getAvailable() + ", pages " + globalMemory.getPageSize();
		VirtualMemory virtualMemory = globalMemory.getVirtualMemory();
		details += " swap memory total " + virtualMemory.getSwapTotal() + " from witch is used "
				+ virtualMemory.getSwapUsed();

		return details;
	}

	public String getNetworkInfo() {
		String details = "";
		NetworkIF[] networkIF = hardwareAbstractionLayer.getNetworkIFs();

		for (int i = 0; i < networkIF.length; i++) {
			details = " network interface " + (i + 1) + " IPv4 " + Arrays.toString(networkIF[i].getIPv4addr())
					+ " IPv6 " + Arrays.toString(networkIF[i].getIPv6addr()) + " MAC address "
					+ networkIF[i].getMacaddr() + " sent bytes " + networkIF[i].getBytesSent() + " receive bytes "
					+ networkIF[i].getBytesSent() + " MTU " + networkIF[i].getMTU();
		}
		
		return details;
	}
	
	public String getInfoOS() {
		return "OS Family : " + operatingSystem.getFamily() + "\n" + "Manufactured : "
				+ operatingSystem.getManufacturer() + "\n" + "Thread number + " + operatingSystem.getThreadCount()
				+ "\n" + "System boot time + " + operatingSystem.getSystemBootTime() + "\n";
	}

	public String getDomainName() {
		
		return operatingSystem.getNetworkParams().getDomainName();
	}
	
	public String getInfoProcesses() {
		String details = "";
		OSProcess [] process =  operatingSystem.getProcesses(0, null);
		
		for(int i = 0; i < process.length; i ++) {
			details += "for process " + (i + 1) + " name: " + process[i].getName() + " group : " + process[i].getGroup() + " group id:" + process[i].getGroupID() + " get parrent process id: " + process[i].getParentProcessID()
					+ " command line: " + process[i].getCommandLine() + "current working directory: " + process[i].getCurrentWorkingDirectory()
					+ " thread count: " + process[i].getThreadCount() + " bitness: " + process[i].getBitness() + " start time: " + process[i].getStartTime()
					+ " priority " + process[i].getPriority() + " bytes read: " + process[i].getBytesRead() + " bytes write: " + process[i].getBytesWritten() + "\n-----------------------------------------------------------------------\n";
		}
		
		return details;
	}
	
	
}
