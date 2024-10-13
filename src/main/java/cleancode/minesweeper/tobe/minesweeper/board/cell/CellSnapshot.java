package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapshot {

  private final CellSnapshotStatus status;
  private final int nearbyLandMineCount;

  private CellSnapshot(CellSnapshotStatus status, int nearbyLandMineCount) {
    this.status = status;
    this.nearbyLandMineCount = nearbyLandMineCount;
  }

  public static CellSnapshot of(CellSnapshotStatus status, int nearbyLandMineCount) {
    return new CellSnapshot(status, nearbyLandMineCount);
  }

  public static CellSnapshot ofEmpty() {
    return new CellSnapshot(CellSnapshotStatus.EMPTY, 0);
  }

  public static CellSnapshot ofFlag() {
    return new CellSnapshot(CellSnapshotStatus.FLAG, 0);
  }

  public static CellSnapshot ofLandMine() {
    return new CellSnapshot(CellSnapshotStatus.LAND_MINE, 0);
  }

  public static CellSnapshot ofNumber(int nearbyLandMineCount) {
    return new CellSnapshot(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
  }

  public static CellSnapshot ofUnchecked() {
    return new CellSnapshot(CellSnapshotStatus.UNCHECKED, 0);
  }

  public int getNearbyLandMineCount() {
    return nearbyLandMineCount;
  }

  public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
    return this.status == cellSnapshotStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CellSnapshot snapshot = (CellSnapshot) o;
    return nearbyLandMineCount == snapshot.nearbyLandMineCount && status == snapshot.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, nearbyLandMineCount);
  }
}
