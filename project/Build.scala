import sbt._
import Keys._

object ScalaBuffBuild extends Build {

  val snapshotsRepo = "iDecide 3rd party Snapshots" at "https://nexus.flexis.ru/content/repositories/thirdparty-snapshots"
  val releasesRepo = "iDecide 3rd party Releases" at "https://nexus.flexis.ru/content/repositories/thirdparty"

  lazy val project = Project(
    id = "root", 
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
      publishTo <<= (version) { version: String =>
        version.contains("-SNAPSHOT")  match {
          case true => Some(snapshotsRepo)
          case false => Some(releasesRepo)
        }
      }
    )
  )
}
