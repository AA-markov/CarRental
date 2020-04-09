package rusoft.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rusoft.project.entity.Client;
import rusoft.project.repository.ClientRepository;

@Component
@RequiredArgsConstructor
public class MainClientListener implements ApplicationListener<ContextRefreshedEvent> {

  private final Client basicClient;
  private final ClientRepository clientRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (!clientRepository.existsByName(basicClient.getName())) {
      clientRepository.save(basicClient);
    }
  }
}
