## Contributing

First of all, thank you for considering contributing to Nexte. It's people
like you that make Nexte such a great app.

### 1. Where do I go from here?

If you've noticed a bug or have a question that doesn't belong on the
[Stack Overflow][] or [search the issue tracker][] to see if
someone else in the community has already created a ticket. If not, go ahead and
[make one][new issue]!

### 2. Política de branches
![Gitflow](https://i.imgur.com/tETmqYN.png)  
_Figura 1 - Política de branches, para mais informações visualizar [essa]() página._

_**Branches hotfix**_ deverão ser utilizadas apenas pelos membros do repositório.

_**Branches feature**_ poderão ser utilizadas pelos membros do repositório e por colaboradores externos. Para adicionar sua colaboração basta seguir os seguintes passos:

* **Clonar o repositório**
```sh
  git clone https://github.com/fga-gpp-mds/2018.1_Nexte/
```

* **Entrar na _branch dev_**
```sh
  git checkout dev
```

* **Criar uma branch com a seguinte nomeação**
```sh
  git checkout -b feature/331
```
_Onde 331 é a issue que está sendo solucionada_

* **Resolver a _issue_**

* **Deixar a sua _branch_ atualizada com a _branch dev_**
```sh
  git pull origin dev
```

* **Subir a _branch_ para o repositório remoto**
```sh
  git pull origin feature/331
```

* **Submeter o _pull request_ da sua _branch_ para a _branch dev_**

* **Esperar e ficar de olho na revisão que será feito por algum membro do repositório**

### 2. Fork & create a branch

If this is something you think you can fix, then [fork Active Admin][] and
create a branch with a descriptive name.

A good branch name would be (where issue #331 is the ticket you're working on):

```sh
git checkout -b hotfix/331
```

<!--
### 3. Get the test suite running
-->

### 4. Did you find a bug?

* **Ensure the bug was not already reported** by [searching all issues][].

* If you're unable to find an open issue addressing the problem,
  [open a new one][new issue]. Be sure to include a **title and clear
  description**, as much relevant information as possible, and a **code sample**
  or an **executable test case** demonstrating the expected behavior that is not
  occurring.

* If possible, use the relevant bug report templates to create the issue.
  Simply copy the content of the appropriate template into a .kt file, make the
  necessary changes to demonstrate the issue, and **paste the content into the
  issue description**:
  * [**ActiveAdmin** master issues][master template]

### 5. Implement your fix or feature

At this point, you're ready to make your changes! Feel free to ask for help;
everyone is a beginner at first :smile_cat:

<!--
### 6. View your changes in a Rails application
-->


### 6. Get the style right

Your patch should follow the same conventions & pass the same code quality
checks as the rest of the project. [Codeclimate][codeclimate] will give you
feedback in this regard. You can check & fix codeclimate's feedback by running
it locally using [Codeclimate's CLI][codeclimate cli], via `codeclimate analyze`.

### 7. Make a Pull Request

At this point, you should switch back to your master branch and make sure it's
up to date with Active Admin's master branch:

```sh
git remote add upstream git@github.com:activeadmin/activeadmin.git
git checkout master
git pull upstream master
```

Then update your feature branch from your local copy of master, and push it!

```sh
git checkout 325-add-japanese-translations
git rebase master
git push --set-upstream origin 325-add-japanese-translations
```

Finally, go to GitHub and [make a Pull Request]().

<!--
Travis CI will run our test suite against all supported Rails versions. We care
about quality, so your PR won't be merged until all tests pass. It's unlikely,
but it's possible that your changes pass tests in one Rails version but fail in
another. In that case, you'll have to setup your development environment (as
explained in step 3) to use the problematic Rails version, and investigate
what's going on!
-->

### 8. Keeping your Pull Request updated

If a maintainer asks you to "rebase" your PR, they're saying that a lot of code
has changed, and that you need to update your branch so it's easier to merge.

To learn more about rebasing in Git, there are a lot of [good][git rebasing]
[resources][interactive rebase] but here's the suggested workflow:

```sh
git checkout 325-add-japanese-translations
git pull --rebase upstream master
git push --force-with-lease 325-add-japanese-translations
```

### 9. Merging a PR (maintainers only)

A PR can only be merged into master by a maintainer if:

* It is passing CI.
* It has been approved by at least two maintainers. If it was a maintainer who
  opened the PR, only one extra approval is needed.
* It has no requested changes.
* It is up to date with current master.

Any maintainer is allowed to merge a PR if all of these conditions are
met.

<!-->
### 11. Shipping a release (maintainers only)

Maintainers need to do the following to push out a release:

* Make sure all pull requests are in and that changelog is current
* Update `version.rb` file and changelog with new version number
* Create a stable branch for that release:

  ```sh
  git checkout master
  git fetch activeadmin
  git rebase activeadmin/master
  # If the release is 2.1.x then this should be: 2-1-stable
  git checkout -b N-N-stable
  git push activeadmin N-N-stable:N-N-stable
  ```

* `bundle exec rake release`
-->

[Stack Overflow]: http://stackoverflow.com/questions/tagged/nexte
[search the issue tracker]: https://github.com/activeadmin/activeadmin/issues?q=something
[new issue]: https://github.com/activeadmin/activeadmin/issues/new
[fork Active Admin]: https://help.github.com/articles/fork-a-repo
[searching all issues]: https://github.com/activeadmin/activeadmin/issues?q=
[master template]: https://github.com/activeadmin/activeadmin/blob/master/lib/bug_report_templates/active_admin_master.rb
[codeclimate]: https://codeclimate.com
[codeclimate cli]: https://github.com/codeclimate/codeclimate
[make a pull request]: https://help.github.com/articles/creating-a-pull-request
[git rebasing]: http://git-scm.com/book/en/Git-Branching-Rebasing
[interactive rebase]: https://help.github.com/articles/interactive-rebase
