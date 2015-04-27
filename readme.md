![Doc'Em](https://raw.githubusercontent.com/JohnMurray/docem/master/public/images/logo_github.jpg)

[![Build Status](https://travis-ci.org/JohnMurray/docem.svg?branch=master)](https://travis-ci.org/JohnMurray/docem)
[![Codacy Badge](https://www.codacy.com/project/badge/2d43c7ea33004844b336d367b0d003cd)](https://www.codacy.com/app/me_11/docem)
[![License](https://img.shields.io/github/license/johnmurray/docem.svg)](https://github.com/JohnMurray/docem/blob/master/LICENSE)
[![Current Release](https://img.shields.io/github/release/johnmurray/docem.svg)](https://github.com/JohnMurray/docem/releases)

Doc'Em is a documentation hosting service focusing on static content-hosting. It
handles per-project versioning and global search across all projects and versions.

__NOTE__ - This project is under pretty heavy development and currently unstable.


## Why?

If you work in any sort of large organization, then you likely have lots of internal
applications. You may also find the documentation in those projects a bit lacking.
Part of this is because developers are lazy and the other part is because of lack of
tooling. This project solves the latter problem. 

## Tooling Problems

### Wiki's
When it comes to good documentation, wiki's are a major problem (not a solution). If
you're wiki cannot be tied to source-control, branched, and versioned then you likely 
do not have an adequate system unless you are spending a _major_ amount of time keeping
it up to date. Good documentation needs to live alongside your code (in the same repo)
so that changes in the docs happen simultaneously with code-changes. 

### Hosting
If you decide to do the right thing and _not_ use a wiki, then you're left trying to
figure out _where_ to host your docs and in what system to write them in. While there are
plenty of systems out there, hanlding versioning well within those system can be hit-or-miss.

Back to the where, in large organizations this can mean lots of little web-servers hosting
docs spread out everywhere. While this can mean a lot of things, it mostly means that
your users will have a hard time remembering where to go for _your_ application's docs
amongst all the others they are already using. This additional mental overhead can
mean they'll just bug your team with more questions instead of referring to your docs.


## Advantages Over Apache + FTP

Being a server for hosting static content, you may wonder why not just use something like Apache
or Nginx to serve your static content and use FTP to manage that content on the server. You
could very well do this and be perfectly fine. Using this software, however, buys you
the following advantages.

### Immutable Versions / Version Locks

TODO: Talk about version locking

### Queryable API

Todo: Talk about Project/Project-Version APIs

### Documentation Search Application

One thing that you will not get when using plain 'ole Apache and FTP is an application
to search all available documentation. This application provides that functionality on
the landing page showing the project's name, description, available versions, and a 
search functionality ot filter what you are looking for. Having all of this certainly
wins out over scanning through a "directory view" in Apache, trying to figure out where
the project you are looking for is, or what a given project does.
