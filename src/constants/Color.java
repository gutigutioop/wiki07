package constants;

/**
 * Color列挙
 * コンソールの文字色を変更するエスケープ文字列を定義

 * @author　Say Consulting Group
 * @version　1.0.0
 */
public enum Color {
  /** 赤 */
  Red("\u001b[00;31m"),
  /** 緑 */
  Green("\u001b[00;32m"),
  /** 黄 */
  Yellow("\u001b[00;33m"),
  /** 紫 */
  Purple("\u001b[00;34m"),
  /** ピンク */
  Pink("\u001b[00;35m"),
  /** シアン */
  Cyan("\u001b[00;36m"),
  /** 色を元に戻す */
  End("\u001b[00m");

  /** 色変更エスケープシーケンス */
  private String c;

  /**
   * 色変更用エスケープシーケンスを指定するコンストラクタ
   * @param c 色変更用エスケープシーケンス
   */
  private Color(String c) {
    this.c = c;
  }

  /**
   * getCメソッド
   * エスケープ文字列のアクセサ
   * @return 色変更用文字列
   */
  public String getC() {
    return this.c;
  }

  /**
   * coloredStrメソッド
   * 色変更エスケープ文字列 + 文字列 + 色を戻すエスケープ文字列を返す
   * @param s 色を変更する文字列
   * @return 色変更用エスケープを含んだ文字列
   */
  public String coloredStr(String s) {
    return String.format("%s%s%s", this.c, s, End.getC());
  }
}
