SSL toolchain bypasser
=========================


This is a simple jar that can be provided to georchestra's tomcats, to redefine
the SSL trustchain java mechanisms, so that every SSL certificates will end up
trusted. This is a big security concern, but might help you to get rid of
trusting issues. This should probably not be used in production, or at your own
risks.
