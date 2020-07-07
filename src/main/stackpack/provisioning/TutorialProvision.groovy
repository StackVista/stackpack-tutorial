import groovy.json.JsonSlurper
import com.stackstate.stackpack.ProvisioningScript
import com.stackstate.stackpack.ProvisioningContext
import com.stackstate.stackpack.ProvisioningIO
import com.stackstate.stackpack.Version

class TutorialProvision extends ProvisioningScript {
  TutorialProvision(ProvisioningContext context) {
    super(context)
  }

  def INTEGRATION_TYPE = "tutorial"
  def INTEGRATION_URL = "tutorial://tutorial-1"

  @Override
  ProvisioningIO<scala.Unit> install(Map<String, Object> config) {
    def templateArguments = [
      'topicName': topicName(),
      'integrationType': INTEGRATION_TYPE,
      'integrationUrl': INTEGRATION_URL
    ]
    templateArguments.putAll(config)

    return context().stackPack().importSnapshot("templates/tutorial-template.stj", templateArguments)
  }

  @Override
  ProvisioningIO<scala.Unit> upgrade(Map<String, Object> config, Version current) {
    return install(config)
  }

  @Override
  void waitingForData(Map<String, Object> config) {
    context().sts().onDataReceived(topicName(), {
      context().sts().provisioningComplete()
    })
  }

  private def topicName() {
  def type = INTEGRATION_TYPE
    def url = INTEGRATION_URL
    return context().sts().createTopologyTopicName(type, url)
  }
}
