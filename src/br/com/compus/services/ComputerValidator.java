package br.com.compus.services;

import java.util.ArrayList;
import java.util.List;

import br.com.compus.models.Computer;

public class ComputerValidator {
  private Computer computer;
  private List<String> errors;

  public ComputerValidator(Computer computer) {
    this.computer = computer;
    this.errors = new ArrayList<String>();
  }

  public static boolean validate(Computer computer) {
    return new ComputerValidator(computer).validate();
  }

  public boolean validate() {
    return (validatePartsPresence() && validateCpuSocket() && validateMemoryType() && validateMemoryQuantity());
  }

  public boolean validatePartsPresence() {
    if (computer.getCpu() != null && computer.getHd() != null && computer.getMotherboard() != null && computer.getMemory() != null) {
      return true;
    }
    errors.add("As peças não foram encontradas!");
    return false;
  }

  private boolean validateCpuSocket() {
    if (computer.getCpu().getSocket().equals(computer.getMotherboard().getSocket())) {
      return true;
    }
    errors.add("O socket não é compatível");
    return false;
  }

  private boolean validateMemoryType() {
    if (computer.getMemory().getType().equals(computer.getMotherboard().getMemoryType())) {
      return true;
    }
    errors.add("O tipo da memória não é compatível");
    return false;
  }

  private boolean validateMemoryQuantity() {
    if (computer.getMemoryQuantity() <= computer.getMotherboard().getMemorySlots()) {
      return true;
    }
    errors.add("A quantidade de memórias não é compatível");
    return false;
  }

  public List<String> getErrors() {
    return errors;
  }
}
