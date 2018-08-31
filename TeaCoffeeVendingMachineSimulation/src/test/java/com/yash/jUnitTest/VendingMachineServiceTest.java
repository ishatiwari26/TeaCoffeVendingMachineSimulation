package com.yash.jUnitTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.daoImpl.VendingMachineDaoImpl;
import com.yash.serviceImpl.VendingMachineServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineServiceTest {
	@InjectMocks
	private VendingMachineServiceImpl vendingMachineServiceImpl;

	@Mock
	private VendingMachineDaoImpl vendingMachineDaoImpl;

	@Test
	public void shouldInitializeContainerWhenRefillingDone() {
		doNothing().when(vendingMachineDaoImpl).initialize();
		vendingMachineServiceImpl.RefillContainers();
	}

	@Test
	public void shouldGetTotalSales() {
		when(vendingMachineDaoImpl.getTotalSales()).thenReturn(new Long(100));
		vendingMachineServiceImpl.getTotalSales();
	}

}
